package com.newroad.user.sns.model.user;

import java.io.Serializable;
import java.util.Date;

public class Lucker implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = -2452236978082436570L;
  
  private Long luckerId;
  
  private SimpleUser simpleUser;
  
  private String work;
  
  private String coordinate;
  
  private String language;

  private String feature;
  
  private Integer countryTripCount;

  private Integer globalTripCount;

  
  /**
   * 当前状态
   */
  private Integer status;
  /**
   * 最后登录时间
   */
  private Date createTime;
  /**
   * 最后操作时间
   */
  private Date updateTime;
  
  public Long getLuckerId() {
    return luckerId;
  }
  public void setLuckerId(Long luckerId) {
    this.luckerId = luckerId;
  }
  
  public String getWork() {
    return work;
  }
  public void setWork(String work) {
    this.work = work;
  }
  public String getCoordinate() {
    return coordinate;
  }
  public void setCoordinate(String coordinate) {
    this.coordinate = coordinate;
  }
  public String getLanguage() {
    return language;
  }
  public void setLanguage(String language) {
    this.language = language;
  }
  public String getFeature() {
    return feature;
  }
  public void setFeature(String feature) {
    this.feature = feature;
  }
  public Integer getCountryTripCount() {
    return countryTripCount;
  }
  public void setCountryTripCount(Integer countryTripCount) {
    this.countryTripCount = countryTripCount;
  }
  public Integer getGlobalTripCount() {
    return globalTripCount;
  }
  public void setGlobalTripCount(Integer globalTripCount) {
    this.globalTripCount = globalTripCount;
  }
  public SimpleUser getSimpleUser() {
    return simpleUser;
  }
  public void setSimpleUser(SimpleUser simpleUser) {
    this.simpleUser = simpleUser;
  }
  public Integer getStatus() {
    return status;
  }
  public void setStatus(Integer status) {
    this.status = status;
  }
  public Date getCreateTime() {
    return createTime;
  }
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }
  public Date getUpdateTime() {
    return updateTime;
  }
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }
  
  
}
