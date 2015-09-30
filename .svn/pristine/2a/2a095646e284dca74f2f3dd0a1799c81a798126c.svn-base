package com.newroad.user.sns.model.friend;

import java.io.Serializable;
import java.util.Date;

import com.newroad.util.cosure.Convertable;

/**
 * @info : 好友
 * @author: xiangping_yu
 * @data : 2013-11-6
 * @since : 1.5
 */
public class Friend implements Serializable {

	private static final long serialVersionUID = 1002434672451982933L;
	private Long friendID;
	private Long userID;
	private Long groupID;
	private Integer userType;
	private String nickName;
	/**
	 * 性别
	 */
	private Integer gender;
	/**
	 * 个人介绍
	 */
	private String description;
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 最后登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 最后操作时间
	 */
	private Date lastOperateTime;

	
	public static final Convertable<Friend, Long> TO_FRIENDID = new Convertable<Friend, Long>() {
		@Override
		public Long convert(Friend q) {
			return q.getFriendID();
		}
	};
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getFriendID() {
		return friendID;
	}
	public void setFriendID(Long friendID) {
		this.friendID = friendID;
	}
	public Long getGroupID() {
		return groupID;
	}
	public void setGroupID(Long groupID) {
		this.groupID = groupID;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
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
