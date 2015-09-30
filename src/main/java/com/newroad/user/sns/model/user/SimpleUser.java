package com.newroad.user.sns.model.user;

import java.io.Serializable;

public class SimpleUser implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 6776337321272524323L;

  private Long userId;
  
  private Integer userRole;

  private String nickName;
  
  private Integer gender;

  private String portrait;
  
  private Integer level;
  
  private String description;
  
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public Integer getUserRole() {
    return userRole;
  }

  public void setUserRole(Integer userRole) {
    this.userRole = userRole;
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

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }
  
  
}
