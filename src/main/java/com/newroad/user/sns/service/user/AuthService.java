package com.newroad.user.sns.service.user;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.filter.HttpMutualGetFilter;
import com.newroad.user.sns.filter.TokenAuthFilter;
import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.model.secure.SecureAppClient;
import com.newroad.user.sns.model.secure.SecureGroup;
import com.newroad.user.sns.model.secure.SecureGroupAccess;
import com.newroad.user.sns.model.user.Account;
import com.newroad.user.sns.model.user.User;
import com.newroad.user.sns.service.SessionService;
import com.newroad.user.sns.service.login.MobileAuthLogin;
import com.newroad.user.sns.service.login.OpenAuthIf;
import com.newroad.user.sns.service.login.OpenAuthQQLogin;
import com.newroad.user.sns.service.login.OpenAuthWechatLogin;
import com.newroad.user.sns.service.login.OpenAuthWeiboLogin;
import com.newroad.user.sns.service.message.sms.EmaySmsManager;
import com.newroad.user.sns.service.message.sms.MessageConstant;
import com.newroad.user.sns.service.message.sms.VerificationCode;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.IDCompress;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;
import com.newroad.util.auth.TokenUtil;

/**
 * 认证服务
 */
public class AuthService {

  private static Logger logger = LoggerFactory.getLogger(AuthService.class);

  private static String AUTH_TYPE = "authtype";

  private static String NO_REGISTER = "noregister";

  private SnsDao snsDao;
  private OpenAuthIf openAuth;
  // private LenovoSTLogin lenovost;
  private MobileAuthLogin mobilAuth;
  private OpenAuthQQLogin openAuthQQ;
  private OpenAuthWeiboLogin openAuthWeibo;
  private OpenAuthWechatLogin openAuthWechat;

  private SessionService sessionService;

  /**
   * 登录认证
   */
  @Transactional(rollbackFor = {Throwable.class}, propagation = Propagation.REQUIRED, timeout = 30)
  public ServiceResult<JSONObject> login(String apiVersion, Map<String, Object> param) {
    ServiceResult<JSONObject> result = new ServiceResult<JSONObject>();
    Integer authType = (Integer) param.get(AUTH_TYPE);
    if(authType==null){
      result.setReturnCode(ReturnCode.BAD_REQUEST);
      result.setReturnMessage("Fail to get authtype parameter!");
      return result;
    }
    switch (authType) {
    // case 1:
    // openAuth = lenovost;
    // break;
      case 2:
        openAuth = mobilAuth;
        break;
      case 3:
        openAuth = openAuthQQ;
        break;
      case 4:
        openAuth = openAuthWeibo;
        break;
      case 5:
        openAuth = openAuthWechat;
        break;
    }
    LoginContext context = openAuth.auth(param);

    if (context.getReturnCode() != ReturnCode.OK) {
      result.setReturnCode(context.getReturnCode());
      result.setReturnMessage(new StringBuffer(context.getReturnMsg()));
      return result;
    }

    Integer noregister = (Integer) param.get(NO_REGISTER);
    User user = null;
    // Get user info using third-party AccountID,but mobile account don't need to check this process
    if (authType != 2) {
      String account = context.getThirdPartyAccount();
      user = snsDao.selectOne("user.getUserByAccountID", account);
      if (user != null) {
        checkUserUpdateStatus(context, user);
        context.setUser(user);
      } else {
        if (noregister != null && noregister == 1) {
          result.setReturnCode(ReturnCode.UNAUTHORIZED);
          result.setReturnMessage("Fail to login without register because user couldn't found in db!");
          logger.error("Fail to login without register because user couldn't found in db!");
          return result;
        }
        logger.info("Register new user in db as account " + account);
        // save user
        saveUser(context);
        // save account
        saveAccount(context);
      }
    }
    // niu ren role is 2
    // if (context.getUser().getUserRole() == 2) {
    // user = context.getUser();
    // Lucker lucker = snsDao.selectOne("lucker.getLuckerByUserID", user.getUserId());
    // user.setLucker(lucker);
    // }

    // save user to session
    LoginUser loginUser = saveSession(context);
    if (loginUser == null) {
      return new ServiceResult<JSONObject>(ReturnCode.UNAUTHORIZED, ControllerUtils.bulid("Fail to auth user info!"), null);
    }
    result.setBusinessResult(JSONObject.fromObject(loginUser));
    return result;
  }

  /**
   * 登出
   */
  public ServiceResult<String> logout(String apiVersion) throws Exception {
    LoginUser current = TokenAuthFilter.getCurrent();
    sessionService.clearLoginUser(current.getToken());
    logger.info("user logout, user id:" + current.getUserId());
    return new ServiceResult<String>();
  }

  public ServiceResult<LoginUser> register(String apiVersion, Map<String, Object> param) throws Exception {
    ServiceResult<LoginUser> result = new ServiceResult<LoginUser>();
    logger.info("user register parametes:" + param);
    String mobile = (String) param.get(MobileAuthLogin.MOBILE);
    String password = (String) param.get(MobileAuthLogin.PASSWORD);
    String nickName = (String) param.get(MobileAuthLogin.NICKNAME);
    String code = (String) param.get(MobileAuthLogin.CODE);

    if (StringUtils.isBlank(mobile)) {
      result.setReturnCode(ReturnCode.BAD_REQUEST);
      result.setReturnMessage(ControllerUtils.bulid("user input parameters are blank!"));
      return result;
    }
    Integer exist = checkUserExist(mobile, nickName);
    if (exist == 1) {
      result.setReturnCode(ReturnCode.BAD_REQUEST);
      logger.error(ControllerUtils.bulid("user mobile has been exist!") + ",mobile=" + mobile);
      result.setReturnMessage(ControllerUtils.bulid("user mobile has been exist!"));
      return result;
    } else if (exist == 2) {
      result.setReturnCode(ReturnCode.BAD_REQUEST);
      logger.error(ControllerUtils.bulid("user nickName has been exist!") + ",nickName=" + nickName);
      result.setReturnMessage(ControllerUtils.bulid("user nickName has been exist!"));
      return result;
    }

    VerificationCode testvc = sessionService.getVerificationCode(mobile);
    if (testvc == null || !code.equals(testvc.getRandomCode())) {
      result.setReturnCode(ReturnCode.NO_AVAILABLE_RANDOM_CODE);
      result.setReturnMessage(ControllerUtils.bulid("random code error!"));
      return result;
    }

    LoginContext context = new LoginContext();
    User user = new User(User.UserType.mobile.getCode(), User.UserRole.user.getCode(), mobile, password, nickName);
    context.setUser(user);
    saveUser(context);
    LoginUser loginUser = saveSession(context);
    if (loginUser == null) {
      return new ServiceResult<LoginUser>(ReturnCode.UNAUTHORIZED, ControllerUtils.bulid("Fail to auth user info!"), null);
    }
    result.setBusinessResult(loginUser);
    return result;
  }

  public ServiceResult<String> checkUserExist(String apiVersion, Map<String, Object> param) throws Exception {
    ServiceResult<String> result = new ServiceResult<String>();
    String mobile = (String) param.get(MobileAuthLogin.MOBILE);
    String loginName = (String) param.get(OpenAuthIf.LOGINNAME);
    if (checkUserExist(mobile, loginName) == 0) {
      result.setReturnMessage(ControllerUtils.bulid("user is availible!"));
      return result;
    }
    result.setReturnCode(ReturnCode.BAD_REQUEST);
    result.setReturnMessage(ControllerUtils.bulid("user has been exist!"));
    return result;
  }

  private Integer checkUserExist(String mobile, String loginName) {
    Map<String, Object> param = new HashMap<String, Object>(2);
    param.put(MobileAuthLogin.MOBILE, mobile);
    param.put(MobileAuthLogin.LOGINNAME, loginName);
    logger.info("Check user exist:" + mobile + "," + loginName);
    User user = snsDao.selectOne("user.checkUserExist", param);
    if (user != null) {
      if (mobile.equals(user.getMobile())) {
        return 1;
      } else if (loginName.equals(user.getLoginName())) {
        return 2;
      }
    }
    return 0;
  }

  public ServiceResult<String> smsMessage(String apiVersion, String mobile, String message) throws Exception {
    ServiceResult<String> result = new ServiceResult<String>();
    boolean sendResult = EmaySmsManager.sendsms(mobile, message);
    if (!sendResult) {
      result.setReturnCode(ReturnCode.SMS_SEND_ERROR);
      result.setReturnMessage(ControllerUtils.bulid("Fail to send sms message!"));
      return result;
    }
    result.setReturnMessage(ControllerUtils.bulid("sms has completed successfully!"));
    return result;
  }


  public ServiceResult<String> smsVerificationCode(String apiVersion, Map<String, Object> param) throws Exception {
    ServiceResult<String> result = new ServiceResult<String>();
    String mobile = (String) param.get(MobileAuthLogin.MOBILE);
    Integer authType = (Integer) param.get(AUTH_TYPE);
    if (StringUtils.isBlank(mobile) || authType == null || "".equals(authType)) {
      result.setReturnCode(ReturnCode.BAD_REQUEST);
      result.setReturnMessage(ControllerUtils.bulid("sms auth service unavailable because the parameters are invalid!"));
      return result;
    }
    String message = "";
    switch (authType) {
      case 1:
        message = MessageConstant.VERIFICATION_MESSAGE;
        break;
      case 2:
        message = MessageConstant.BACK_PASSWORD_MESSAGE;
        break;
    }
    boolean sendResult = sendVerificationCode(mobile, message);
    if (!sendResult) {
      result.setReturnCode(ReturnCode.NO_AVAILABLE_RANDOM_CODE);
      result.setReturnMessage(ControllerUtils.bulid("Fail to generate sms verification code!"));
      return result;
    }
    result.setReturnMessage(ControllerUtils.bulid("sms has completed successfully!"));
    return result;
  }

  private boolean sendVerificationCode(String mobilePhone, String message) {
    boolean sendResult = false;
    VerificationCode vc = null;

    String code = MessageConstant.generateRandomCode();
    boolean result = EmaySmsManager.sendsms(mobilePhone, String.format(message, code));
    if (result) {
      vc = new VerificationCode();
      vc.setRandomCode(code);
      vc.setSendTime(System.currentTimeMillis());
      boolean cacheStatus = sessionService.setVerificationCode(mobilePhone, vc, MessageConstant.VERIFICATION_CODE_CACHE_TIME);

      VerificationCode testvc = sessionService.getVerificationCode(mobilePhone);
      logger.info("VerificationCode cache data:" + testvc.getRandomCode());
      if (cacheStatus) {
        sendResult = true;
      }
    }
    return sendResult;
  }

  /**
   * 检查登录是否有效，并返回基本信息
   */
  public ServiceResult<JSONObject> check(Map<String, Object> param) throws Exception {
    String token = HttpMutualGetFilter.getRequestHeader(TokenUtil.TOKEN);
    if (StringUtils.isBlank(token)) {
      return new ServiceResult<JSONObject>(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("Parameter [" + TokenUtil.TOKEN + "] not found!"),
          null);
    }

    LoginUser login = sessionService.getLoginUser(token);
    if (login == null) {
      if (!sessionService.isAvailable()) {
        return new ServiceResult<JSONObject>(ReturnCode.AUTH_UNAVAILABLE, ControllerUtils.bulid("auth service unavailable!"), null);
      }
      return new ServiceResult<JSONObject>(ReturnCode.ILLEGAL_TOKEN, ControllerUtils.bulid("user no login!"), null);
    }
    JSONObject userInfo = JSONObject.fromObject(login);
    // logger.info("check login user:" + userInfo);
    return new ServiceResult<JSONObject>(userInfo);
  }

  public ServiceResult<String> resetPassword(Map<String, Object> param) throws Exception {
    ServiceResult<String> result = new ServiceResult<String>();

    String mobile = (String) param.get(MobileAuthLogin.MOBILE);
    String code = (String) param.get(MobileAuthLogin.CODE);

    VerificationCode testvc = sessionService.getVerificationCode(mobile);
    if (testvc == null || !code.equals(testvc.getRandomCode())) {
      result.setReturnCode(ReturnCode.NO_AVAILABLE_RANDOM_CODE);
      result.setReturnMessage(ControllerUtils.bulid("random code error!"));
      return result;
    }
    int updateCount = snsDao.update("user.resetPassword", param);

    if (updateCount == 0) {
      result.setReturnCode(ReturnCode.AUTH_UNAVAILABLE);
      result.setReturnMessage(ControllerUtils.bulid("Fail to reset user password!"));
      return result;
    }
    result.setReturnMessage(ControllerUtils.bulid("Reset user password successfully!"));
    return result;
  }

  /**
   * 安全认证
   */
  public ServiceResult<JSONObject> security(Map<String, Object> param) throws Exception {
    String serviceName = (String) param.get("serviceName");
    String consumerKey = (String) param.get("consumerKey");
    String remoteIp = (String) param.get("remoteIp");

    // $1: 查询应用信息、已被禁用时返回不可用
    SecureAppClient client = (SecureAppClient) snsDao.selectOne("secure.getSecureAppClient", param);
    if (client == null) { // 无该key配置
      logger.warn("Consumer client unavailable, service is [" + serviceName + "], key is [" + consumerKey + "], ip is [" + remoteIp + "]");
      return new ServiceResult<JSONObject>(ReturnCode.CONSUMER_KEY_UNAVAILABLE, ControllerUtils.bulid("Consumer key unavailable!"), null);
    }
    if (client.getStatus() == 0 || client.getAppStatus() == 0) { // 审核中
      logger.warn("Consumer client verify, service is [" + serviceName + "], key is [" + consumerKey + "], ip is [" + remoteIp + "]");
      return new ServiceResult<JSONObject>(ReturnCode.CONSUMER_KEY_VERIFY, ControllerUtils.bulid("Consumer key verifying!"), null);
    }
    if (client.getStatus() != 1 || client.getAppStatus() != 1) { // 已停用
      logger.warn("Consumer client disable, service is [" + serviceName + "], key is [" + consumerKey + "], ip is [" + remoteIp + "]");
      return new ServiceResult<JSONObject>(ReturnCode.CONSUMER_KEY_DISABLE, ControllerUtils.bulid("Consumer key disable!"), null);
    }

    // $2: 验证客户端所属组权限
    SecureGroup group = (SecureGroup) snsDao.selectOne("secure.findSecureGroupById", client.getGroupId());
    if (group.getType() != 0) {
      SecureGroupAccess access = (SecureGroupAccess) snsDao.selectOne("secure.findSecureAccess", param);
      if (access == null) {
        logger.warn("No api access, consumer key is [" + consumerKey + ", url:" + param.get("url"));
        return new ServiceResult<JSONObject>(ReturnCode.NO_API_ACCESS, ControllerUtils.bulid("Consumer key request uri no access!"), null);
      }
    }

    JSONObject json = new JSONObject();
    json.put("client-secret", client.getSecret());
    return new ServiceResult<JSONObject>(json);
  }

  private void saveUser(LoginContext context) {
    User user = context.getUser();
    if (user == null) {
      user = new User();
      String userName = context.getUserName();
      if (userName == null) {
        userName = "New_" + context.getThirdPartyAccount();
      }
      user.setLoginName(userName);
      user.setNickName(userName);
      user.setUserType(context.getUserAuthType());
      user.setShortId(getShortID(context.getThirdPartyAccount()));
      // user.setPortrait("");
    }
    snsDao.insert("user.registUser", user);
    context.setUser(user);
  }

  private User checkUserUpdateStatus(LoginContext loginContext, User currentUser) {
    User contextUser = loginContext.getUser();
    Map<String, Object> mapparams = new HashMap<String, Object>();
    if (contextUser != null) {
      String nickName = contextUser.getNickName();
      if (nickName != null && !nickName.equals(currentUser.getNickName())) {
        mapparams.put("nickName", nickName);
        currentUser.setNickName(nickName);
      }
      String loginName = contextUser.getLoginName();
      if (loginName != null && !loginName.equals(currentUser.getLoginName())) {
        mapparams.put("loginName", loginName);
        currentUser.setLoginName(loginName);
      }
      Integer gender = contextUser.getGender();
      if (gender != null && !gender.equals(currentUser.getGender())) {
        mapparams.put("gender", gender);
        currentUser.setGender(gender);
      }
      String portrait = contextUser.getPortrait();
      if (portrait != null && !portrait.equals(currentUser.getPortrait())) {
        mapparams.put("portrait", portrait);
        currentUser.setPortrait(portrait);
      }
      String email = contextUser.getEmail();
      if (email != null && !email.equals(currentUser.getEmail())) {
        mapparams.put("email", email);
        currentUser.setEmail(email);
      }
      snsDao.update("user.editUser", currentUser);
    }
    return currentUser;
  }

  private Account saveAccount(LoginContext context) {
    Account account = new Account();
    account.setUserID(context.getUser().getUserId());
    account.setSource(context.getUserAuthType());
    account.setAccount(context.getThirdPartyAccount());
    account.setSubSource(context.getSubSource());
    snsDao.insert("user.saveAccount", account);
    return account;
  }

  private LoginUser saveSession(LoginContext context) {
    StringBuffer stoken = new StringBuffer();
    stoken.append(UUID.randomUUID().toString());
    stoken.append(UUID.randomUUID().toString());

    String token = stoken.substring(0, 64);

    context.setToken(token);

    User user = context.getUser();
    LoginUser login = new LoginUser();
    login.setToken(token);
    // login.setLpsust(context.getLpsust());
    // login.setRealm(context.getRealm());
    login.setUserId(user.getUserId());
    login.setAccount(context.getThirdPartyAccount());
    login.setUserAuthType(context.getUserAuthType());
    login.setNickName(user.getNickName());
    login.setPortrait(user.getPortrait());
    login.setMobile(user.getMobile());
    login.setUserRole(user.getUserRole());
    login.setOpenId(context.getSubSource());

    logger.info("User login,save session token:" + token);
    if (sessionService.setLoginUser(token, login)) {
      return login;
    }
    return null;
  }

  private String getShortID(String account) {
    String[] shortIDs = IDCompress.compressID(account);
    List<String> uniques = snsDao.selectList("user.getUniqueShortID", Arrays.asList(shortIDs));
    if (CollectionUtils.isEmpty(uniques)) {
      return shortIDs[0];
    }
    if (uniques.size() < shortIDs.length) {
      for (String id : shortIDs) {
        if (!uniques.contains(id)) {
          return id;
        }
      }
    }
    return getShortID(account);
  }

  public void setSessionService(SessionService sessionService) {
    this.sessionService = sessionService;
  }


  public void setSnsDao(SnsDao snsDao) {
    this.snsDao = snsDao;
  }

  public void setMobilAuth(MobileAuthLogin mobilAuth) {
    this.mobilAuth = mobilAuth;
  }

  public void setOpenAuthQQ(OpenAuthQQLogin openAuthQQ) {
    this.openAuthQQ = openAuthQQ;
  }

  public void setOpenAuthWeibo(OpenAuthWeiboLogin openAuthWeibo) {
    this.openAuthWeibo = openAuthWeibo;
  }

  public void setOpenAuthWechat(OpenAuthWechatLogin openAuthWechat) {
    this.openAuthWechat = openAuthWechat;
  }

}
