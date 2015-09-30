package com.newroad.user.sns.service.login;

import java.util.Map;

import com.newroad.user.sns.model.login.LoginContext;


public interface OpenAuthIf {
  
  public static String LOGINNAME = "loginName";//unique
  
  public static String ACCESS_TOKEN_KEY = "access_token";
  
  public static String OPEN_ID_KEY = "openid";

  public static String AVATAR_BOY_PORTRAIT="http://7xjqgn.com1.z0.glb.clouddn.com/d58702ce8d634c5bad89276742ea8237.png";
  
  public static String AVATAR_GIRL_PORTRAIT="http://7xjqgn.com1.z0.glb.clouddn.com/2479d4521e564ea4a605377f06dc9e60.png";
  
  public LoginContext auth(Map<String, Object> para);
  
  public PostAuthResponse executePostAuthTask(LoginContext context) throws Exception;

}
