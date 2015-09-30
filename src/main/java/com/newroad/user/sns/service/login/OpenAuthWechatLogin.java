package com.newroad.user.sns.service.login;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.user.User;
import com.newroad.util.StringHelper;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.encoding.EmojiFilter;
import com.newroad.util.encoding.StringEncodeUtil;
import com.newroad.util.exception.AuthException;

@Service
public class OpenAuthWechatLogin implements OpenAuthIf {
  private static final Logger log = LoggerFactory.getLogger(OpenAuthWechatLogin.class);

  private static String OPEN_ID_VALUE;
  // private static String AUTH_URL = "https://api.weixin.qq.com/sns/auth";
  private static String AUTH_USER_INFO_URL = "https://api.weixin.qq.com/sns/userinfo";


  public LoginContext auth(Map<String, Object> para) {
    log.info("Auth Weixin account: authinfo[" + para + "]");

    LoginContext context = new LoginContext();

    String accessToken = (String) para.get(ACCESS_TOKEN_KEY);
    // String appId = (String) para.get(APP_ID_KEY);
    // open wechat developer id
    OPEN_ID_VALUE = (String) para.get(OPEN_ID_KEY);
    if (StringUtils.isBlank(accessToken) || StringUtils.isBlank(OPEN_ID_VALUE)) {
      context.setReturnCode(ReturnCode.BAD_REQUEST);
      context.setReturnMsg("Weixin auth parameter not found!");
      return context;
    }

    context.setUserAuthType(User.UserType.wechat.getCode());
    context.setToken(accessToken.trim());
    // context.setAppUniqueID(appId);
    // context.setThirdPartyAccount(openId);
    try {
      // 解析认证结果
      distribute(executePostAuthTask(context).getResponse(), context);
    } catch (Exception e) {
      log.error("Weixin auth error:", e);
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg("auth Weixin error!");
    }
    return context;
  }


  /**
   * connect service
   */
  public PostAuthResponse executePostAuthTask(LoginContext context) throws Exception {
    HttpClient client = new HttpClient();
    client.getParams().setContentCharset("UTF-8");
    // String authURL =
    // AUTH_URL + "?" + ACCESS_TOKEN + "=" +
    // context.getToken()+"&"+OPEN_ID+"="+context.getAccountID();
    // log.info("Weixin auth user info URL:" + authURL);

    String userInfoURL = AUTH_USER_INFO_URL + "?" + ACCESS_TOKEN_KEY + "=" + context.getToken() + "&" + OPEN_ID_KEY + "=" + OPEN_ID_VALUE;
    GetMethod get = new GetMethod(userInfoURL);
    try {
      get.getParams().setContentCharset("UTF-8");
      // get.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
      client.executeMethod(get);
      InputStream in = get.getResponseBodyAsStream();
      // 这里的编码规则要与上面的相对应
      BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
      return new PostAuthResponse(get.getStatusCode(), StringHelper.convertReader2String(br));
    } finally {
      get.releaseConnection();
    }
  }

  /**
   * 分发处理结果
   */
  private void distribute(String response, LoginContext context) throws Exception {
    String encoding = StringEncodeUtil.getEncoding(response);
    response = EmojiFilter.filterEmoji(response);
    log.info("Weixin Response info:" + response + ",encoding:" + encoding);
    JSONObject json = JSONObject.fromObject(response);
    if (json.get("errcode") != null) {
      log.error("Weixin get User Info Response Error:" + json);
      // int returecode=json.getInt("errcode");
      String returnMessage = json.getString("errmsg");
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg("Weixin get User Info Response Error:" + returnMessage);
      return;
    }
    String userName = json.getString("nickname");
    context.setUserName(userName);
    User user = new User();
    user.setLoginName(userName);
    user.setNickName(userName);
    Integer gender = json.getInt("sex");
    // wechat gender type
    if (gender == 0 || gender == null) {
      gender = 1;
    }
    user.setGender(gender);
    String picture1 = json.getString("headimgurl");
    if (picture1 == null || "".equals(picture1)) {
      switch (gender) {
        case 1:
          user.setPortrait(AVATAR_BOY_PORTRAIT);
          break;
        case 2:
          user.setPortrait(AVATAR_GIRL_PORTRAIT);
          break;
      }
    } else {
      user.setPortrait(picture1);
    }
    user.setLocation(json.getString("city"));
    user.setUserType(User.UserType.wechat.getCode());

    // user open unique unionid based on multiple apps
    if (json.containsKey("unionid")) {
      String unionid = json.getString("unionid");
      context.setThirdPartyAccount(unionid);
    } else {
      log.error("Fail to get the correct unionid from weixin response!");
      throw new AuthException("Fail to get the unionid from weixin response!");
    }
    // user openid for the specific andriodApp or webApp
    context.setSubSource(OPEN_ID_VALUE);

    // context.setSubSource(OPEN_ID_VALUE);
    context.setUser(user);
  }
}
