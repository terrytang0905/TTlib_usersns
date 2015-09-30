package com.newroad.user.sns.model.message;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * @info : 消息模型
 * @author: xiangping_yu
 * @data : 2013-11-7
 * @since : 1.5
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 4656752335929039234L;
	private Long messageID;
	private Long userID;
	private Long receiverID;
	private String title;
	private String content;
	/**
	 * 消息范围
	 */
	private Integer scope;
	private Date sendTime;
	private Date readTime;
	/**
	 * 消息有效时间  默认三个月
	 */
	private Date validTime = initValidTime();

	private Date initValidTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}
	public Long getMessageID() {
		return messageID;
	}
	public void setMessageID(Long messageID) {
		this.messageID = messageID;
	}
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public Long getReceiverID() {
		return receiverID;
	}
	public void setReceiverID(Long receiverID) {
		this.receiverID = receiverID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getScope() {
		return scope;
	}
	public void setScope(Integer scope) {
		this.scope = scope;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public Date getReadTime() {
		return readTime;
	}
	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}
	public Date getValidTime() {
		return validTime;
	}
	public void setValidTime(Date validTime) {
		this.validTime = validTime;
	}
}
