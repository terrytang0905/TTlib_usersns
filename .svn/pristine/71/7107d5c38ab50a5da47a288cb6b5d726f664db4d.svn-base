package com.newroad.user.sns.service.login;

import java.io.IOException;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.user.User;
import com.newroad.user.sns.util.CommonUtils;
import com.newroad.util.apiresult.ReturnCode;

@Service
public class OpenAuthQQLogin implements OpenAuthIf {
  private static final Logger log = LoggerFactory.getLogger(OpenAuthQQLogin.class);
  
  private static String APP_ID_KEY = "client_id";
  private static String CONSUMER_KEY = "oauth_consumer_key";
  private static String AUTH_URL = "https://graph.qq.com/oauth2.0/me";
  private static String USERINFO_URL = "https://graph.qq.com/user/get_user_info";


  public LoginContext auth(Map<String, Object> para) {
    log.info("Auth OpenQQ account: authinfo[" + para + "]");

    LoginContext context = new LoginContext();

    String accessToken = (String) para.get(ACCESS_TOKEN_KEY);
    // String appId = (String) para.get(APP_ID);
    // String openId = (String) para.get(OPEN_ID);
    if (StringUtils.isBlank(accessToken)) {
      context.setReturnCode(ReturnCode.BAD_REQUEST);
      context.setReturnMsg("OpenQQ auth parameter not found!");
      return context;
    }

    context.setUserAuthType(User.UserType.qq.getCode());
    context.setToken(accessToken);
    // context.setAppUniqueID(appId);
    // context.setAccountID(openId);
    try {
      callOpenQQAccount(context);
      // 解析认证结果
      distribute(executePostAuthTask(context).getResponse(), context);
    } catch (Exception e) {
      log.error("OpenQQ auth error:", e);
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg("auth OpenQQ error!");
    }
    return context;
  }

  private void callOpenQQAccount(LoginContext context) throws HttpException, IOException {
    HttpClient client = new HttpClient();
    GetMethod get = null;
    try {
      String authURL = AUTH_URL + "?" + ACCESS_TOKEN_KEY + "=" + context.getToken();
      get = new GetMethod(authURL);
      client.executeMethod(get);
      String responseStr = get.getResponseBodyAsString();
      int beginIndex = responseStr.indexOf("{");
      int endIndex = responseStr.indexOf("}");
      responseStr = responseStr.substring(beginIndex, endIndex + 1);
      JSONObject json = JSONObject.fromObject(responseStr);
      String appId = json.getString(APP_ID_KEY);
      context.setAppUniqueID(appId);
      String openId = json.getString(OPEN_ID_KEY);
      context.setThirdPartyAccount(openId);
    } finally {
      get.releaseConnection();
    }
  }

  /**
   * connect service
   */
  public PostAuthResponse executePostAuthTask(LoginContext context) throws Exception {
    HttpClient client = new HttpClient();

    String extendURL =
        USERINFO_URL + "?" + ACCESS_TOKEN_KEY + "=" + context.getToken() + "&" + CONSUMER_KEY + "=" + context.getAppUniqueID() + "&" + OPEN_ID_KEY
            + "=" + context.getThirdPartyAccount() + "&format=json";
    log.info("OpenQQ get user info URL:" + extendURL);
    GetMethod get = new GetMethod(extendURL);
    try {
      client.executeMethod(get);
      return new PostAuthResponse(get.getStatusCode(), get.getResponseBodyAsString());
    } finally {
      get.releaseConnection();
    }
  }

  /**
   * 分发处理结果
   */
  private void distribute(String response, LoginContext context) throws DocumentException {
    log.info("QQ Response info:" + response);
    JSONObject json = JSONObject.fromObject(response);
    Integer returnCode = json.getInt("ret");
    if (returnCode != 0) {
      log.error("OpenQQ get User Info Response Error!");
      return;
    }
    String userName = json.getString("nickname");
    context.setUserName(userName);
    User user = new User();
    user.setLoginName(userName);
    user.setNickName(userName);
    String genderStr = json.getString("gender");
    Integer gender = 1;
    String genderUnicode = CommonUtils.toUnicode(genderStr);
    gender = genderUnicode.equals("7537\t") ? 1 : 2;
    user.setGender(gender);
    String picture1 = json.getString("figureurl_qq_1");
    String picture2 = json.getString("figureurl_qq_2");
    if (picture2 != null && !"".equals(picture2)) {
      user.setPortrait(picture2);
    } else {
      user.setPortrait(picture1);
    }
    user.setLocation(json.getString("province"));
    user.setUserType(User.UserType.qq.getCode());
    context.setUser(user);
  }

}
