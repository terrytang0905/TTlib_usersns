package com.newroad.user.sns.service.login;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.user.User;
import com.newroad.util.apiresult.ReturnCode;

@Service
public class MobileAuthLogin implements OpenAuthIf {
  private static final Logger log = LoggerFactory.getLogger(MobileAuthLogin.class);

  public static String MOBILE = "mobile";
  public static String PASSWORD = "password";
  public static String NICKNAME = "nickName";//not unique
  public static String CODE = "code";

  private SnsDao snsDao;

  @Override
  public LoginContext auth(Map<String, Object> para) {
    log.info("Auth Mobile account: authinfo[" + para + "]");

    LoginContext context = new LoginContext();

    String mobile = (String) para.get(ACCESS_TOKEN_KEY);
    String password = (String) para.get(OPEN_ID_KEY);
    String nickName=(String) para.get(NICKNAME);

    if (StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
      context.setReturnCode(ReturnCode.BAD_REQUEST);
      context.setReturnMsg("Mobile auth parameter not found!");
      return context;
    }
    User user = verifyMobileAccountExist(mobile);
    if (user == null) {
        context.setReturnCode(ReturnCode.BAD_REQUEST);
        context.setReturnMsg("Mobile auth user info not found!");
        return context;
    }else{
      String pwd = user.getPassword();
      if (!password.equals(pwd)) {
        context.setReturnCode(ReturnCode.BAD_REQUEST);
        context.setReturnMsg("Mobile auth password invalid!");
        return context;
      }
      context.setUser(user);
    }

    context.setUserAuthType(User.UserType.mobile.getCode());
    // mobile account
    context.setThirdPartyAccount(mobile);
    // password
    context.setVerified(password);
    // use mobile as username
    context.setUserName(user.getLoginName());
    try {
      // 解析认证结果
      distribute(null, context);
    } catch (Exception e) {
      log.error("lenovo st auth error:", e);
      context.setReturnCode(ReturnCode.UNAUTHORIZED);
      context.setReturnMsg("auth lenovo st error!");
    }
    return context;
  }

  private User verifyMobileAccountExist(String mobile) {
    User user = snsDao.selectOne("user.getUserByPhone", mobile);
    return user;
  }

  // private User verifyMobileUserAccount(String mobile, String password) {
  // User userParam = new User(mobile, password);
  // User user = snsDao.selectOne("user.verifyUserByMobileAccount", userParam);
  // return user;
  // }

  /**
   * connect service
   */
  @Override
  public PostAuthResponse executePostAuthTask(LoginContext context) throws Exception {
    return null;
  }

  /**
   * 分发处理结果
   */
  private void distribute(String response, LoginContext context) throws DocumentException {
    log.info("Response info:" + response);
//    User user = context.getUser();
//    if (user == null) {
//      user = new User();
//      String userName = context.getUserName();
//      user.setLoginName(userName);
//      user.setNickName(userName);
//      user.setPhone(context.getAccountID());
//      user.setPassWord(context.getVerified());
//      user.setUserType(User.UserType.mobile.getCode());
//      snsDao.insert("user.registUser", user);
//    }
//    user.setUserType(User.UserType.mobile.getCode());
//    context.setUser(user);
  }

  public void setSnsDao(SnsDao snsDao) {
    this.snsDao = snsDao;
  }

}
