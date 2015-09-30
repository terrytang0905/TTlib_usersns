package com.newroad.user.sns.service.login;

import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.user.User;
import com.newroad.util.apiresult.ReturnCode;

@Service
public class OpenAuthWeiboLogin implements OpenAuthIf {

  private static final Logger log = LoggerFactory.getLogger(OpenAuthWeiboLogin.class);
  
  private static String APP_KEY = "appkey";
  private static String UID = "uid";
  private static String AUTH_URL = "https://api.weibo.com/2/users/show.json";


  public LoginContext auth(Map<String, Object> para) {
    log.info("Auth OpenWeibo account: authinfo[" + para + "]");

    LoginContext context = new LoginContext();

    String accessToken = (String) para.get(ACCESS_TOKEN_KEY);
    String appKey = (String) para.get(APP_KEY);
    String uid = para.get(UID).toString();
    if (StringUtils.isBlank(accessToken) || uid == null) {
      context.setReturnCode(ReturnCode.BAD_REQUEST);
      context.setReturnMsg("OpenWeibo auth parameter not found!");
      return context;
    }

    context.setUserAuthType(User.UserType.weibo.getCode());
    context.setToken(accessToken);
    context.setAppUniqueID(appKey);
    context.setThirdPartyAccount(uid);
    try {
      // 解析认证结果
      distribute(executePostAuthTask(context).getResponse(), context);
    } catch (Exception e) {
      log.error("OpenWeibo auth error:", e);
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg("auth OpenWeibo error!");
    }
    return context;
  }

  /**
   * connect service
   */
  public PostAuthResponse executePostAuthTask(LoginContext context) throws Exception {
    HttpClient client = new HttpClient();
    Long uid = Long.valueOf(context.getThirdPartyAccount());
    String extendURL = AUTH_URL + "?" + ACCESS_TOKEN_KEY + "=" + context.getToken() + "&" + UID + "=" + uid;
    log.info("OpenWeibo get user info URL:" + extendURL);
    GetMethod get = new GetMethod(extendURL);
    try {
      get.getParams().setContentCharset("UTF-8");
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
    log.info("Weibo Response info:" + response);
    if (response.indexOf("error") >= 0||response.indexOf("Sorry") >= 0) {
      String errMsg="OpenWeibo get User Info Response Error:"+response;
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg(errMsg);
      return;
    }
    JSONObject json = JSONObject.fromObject(response);
    String screenName = json.getString("screen_name");
    String name = json.getString("name");
    if (screenName != null && !"".equals(screenName)) {
      context.setUserName(screenName);
    } else {
      context.setUserName(name);
    }
    User user = new User();
    user.setLoginName(context.getUserName());
    user.setNickName(context.getUserName());
    String genderStr = json.getString("gender");
    Integer gender = genderStr.equals("m") ? 1 : 2;
    user.setGender(gender);
    String profileImage = json.getString("profile_image_url");
    log.info("Weibo user " + context.getUserName() + " ,profileImage " + profileImage);
    user.setPortrait(profileImage);
    user.setLocation(json.getString("location"));
    user.setUserType(User.UserType.weibo.getCode());
    context.setUser(user);
  }

}
