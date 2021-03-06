package com.newroad.user.sns.model.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户 (User info in DB)
 */
public class User implements Serializable {

  private static final long serialVersionUID = -9073355599858333309L;

  private Long userId;

  /**
   * 登录名
   */
  private String loginName;
  /**
   * 昵称
   */
  private String nickName;
  /**
   * 密码
   */
  // private transient String passWord;
  private transient String password;
  /**
   * 用户登录类型
   */
  private Integer userType;

  /**
   * admin=1/lucker=2/user=3
   */
  private Integer userRole = 3;
  /**
   * 当前状态
   */
  private Integer status;
  /**
   * 短ID,用于推广链接
   */
  private String shortId;
  /**
   * 真实名字
   */
  private String realName;
  /**
   * 电话
   */
  private String mobile;
  /**
   * 个人介绍
   */
  private String description;
  /**
   * 头像
   */
  private String portrait;

  /**
   * 性别 1:nan 2:nu
   */
  private Integer gender;
  /**
   * 生日
   */
  private Date birthday;

  private Integer level;
  /**
   * 电子邮箱
   */
  private String email;
  /**
   * sns
   */
  private String sns;
  /**
   * 工作
   */
  private String work;

  /**
   * 所在地City
   */
  private String location;
  /**
   * 邮编
   */
  private String zip;
  /**
   * 地址
   */
  private String address;

  /**
   * 大学
   */
  private String college;

//  // Temp
//  private Lucker lucker;
  /**
   * 最后登录时间
   */
  private Date lastLoginTime;
  /**
   * 最后操作时间
   */
  private Date lastOperateTime;

  /**
   * @info : User login type
   * @author: tangzj
   * @data : 2013-11-4
   * @since : 1.5
   */
  public static enum UserType {
    def(0), lenovo(1), mobile(2), qq(3), weibo(4), wechat(5);

    private int code;

    private UserType(int code) {
      this.code = code;
    }

    public static UserType fromCode(Integer code) {
      if (code == null)
        return def;
      for (UserType ut : values()) {
        if (code.equals(ut.getCode()))
          return ut;
      }
      return def;
    }

    public int getCode() {
      return code;
    }
  }

  public static enum UserRole {
    admin(1), lucker(2), user(3);

    private int code;

    private UserRole(int code) {
      this.code = code;
    }

    public static UserRole fromCode(Integer code) {
      if (code == null)
        return user;
      for (UserRole ut : values()) {
        if (code.equals(ut.getCode()))
          return ut;
      }
      return user;
    }

    public int getCode() {
      return code;
    }
  }

  public User() {
    super();
  }

  public User(Integer userType, Integer userRole, String mobile, String password, String nickName) {
    super();
    this.userType = userType;
    this.userRole = userRole;
    this.mobile = mobile;
    this.loginName = mobile;
    this.nickName = nickName;
    this.password = password;
  }

  // Mobile account info
  public User(String mobile, String password) {
    super();
    this.mobile = mobile;
    this.password = password;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getShortId() {
    return shortId;
  }

  public void setShortId(String shortId) {
    this.shortId = shortId;
  }

  public Integer getUserType() {
    return userType;
  }

  public void setUserType(Integer userType) {
    this.userType = userType;
  }

  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
  }

  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getRealName() {
    return realName;
  }

  public void setRealName(String realName) {
    this.realName = realName;
  }

  public String getMobile() {
    return mobile;
  }

  public void setMobile(String mobile) {
    this.mobile = mobile;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPortrait() {
    return portrait;
  }

  public void setPortrait(String portrait) {
    this.portrait = portrait;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
    this.birthday = birthday;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getSns() {
    return sns;
  }

  public void setSns(String sns) {
    this.sns = sns;
  }

  public String getWork() {
    return work;
  }

  public void setWork(String work) {
    this.work = work;
  }

  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getCollege() {
    return college;
  }

  public void setCollege(String college) {
    this.college = college;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Date getLastOperateTime() {
    return lastOperateTime;
  }

  public void setLastOperateTime(Date lastOperateTime) {
    this.lastOperateTime = lastOperateTime;
  }


}
