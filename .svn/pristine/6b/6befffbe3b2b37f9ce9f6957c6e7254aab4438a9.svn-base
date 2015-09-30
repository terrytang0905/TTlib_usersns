package com.newroad.user.sns.service.message;

import java.util.List;
import java.util.Map;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.model.message.Message;
import com.newroad.user.sns.model.message.MessageConfig;

/**
 * @info  : 系统消息发送器 
 * @author: xiangping_yu
 * @data  : 2013-11-12
 * @since : 1.5
 */
public class Sender {
	
	private SnsDao snsDao;
	private Map<Integer, Template> templates;
	
	public void send (Integer template, final Long sender, final Long receiver, final Map<String,Object> data) {
		Template temp = templates.get(template);
		if(temp==null)
			return;
		
		// 实时发送
		List<MessageConfig> immediate = temp.getImmediate();
		for(MessageConfig config : immediate) {
			Message message = new Message();
			message.setUserID(sender);
			message.setReceiverID(receiver);
			message.setTitle(getFormat(config.getTitle(), data));
			message.setContent(getFormat(config.getMsg(), data));
			send(message);
		}
		
		// 异步发送
		final List<MessageConfig> later = temp.getLater();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(MessageConfig config : later) {
					Message message = new Message();
					message.setUserID(sender);
					message.setReceiverID(receiver);
					message.setTitle(getFormat(config.getTitle(), data));
					message.setContent(getFormat(config.getMsg(), data));
					send(message);
				}
			}
		}).start();
	}
	
	private void send (Message message) {
		snsDao.insert("sendMessage", message);
	}
	
	private String getFormat(String format, Map<String,Object> data) {
		
		return format;
	}

	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
	public void setTemplates(Map<Integer, Template> templates) {
		this.templates = templates;
	}
}
