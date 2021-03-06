package com.newroad.user.sns.model.login;

import java.io.Serializable;

/**
 * @info : 登录用户信息(Cache User info)
 * @author: tangzj
 * @data : 2013-11-8
 * @since : 1.5
 */
public class LoginUser implements Serializable {

  private static final long serialVersionUID = -7034321680572039131L;

  public static final String USERID = "userId";

  private Long userId;
  private String token;
  // LenovoAccountID,weixin unionId(user unique id)
  private String account;
  private Integer userAuthType;
  private String nickName;
  private String portrait;
  private String mobile;
  private Integer userRole;
  //weixin openid
  private String openId;

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getPortrait() {
    return portrait;
  }

  public void setPortrait(String portrait) {
    this.portrait = portrait;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
  }

  public Integer getUserAuthType() {
    return userAuthType;
  }

  public void setUserAuthType(Integer userAuthType) {
    this.userAuthType = userAuthType;
  }

  public String getOpenId() {
    return openId;
  }

  public void setOpenId(String openId) {
    this.openId = openId;
  }

}
