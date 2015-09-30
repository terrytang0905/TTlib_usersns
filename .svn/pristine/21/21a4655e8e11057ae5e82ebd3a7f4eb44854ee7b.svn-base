package com.newroad.user.sns.service.message;

import java.util.List;
import java.util.Map;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.model.message.Message;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;

/**
 * @info  : 消息服务
 * @author: xiangping_yu
 * @data  : 2013-10-29
 * @since : 1.5
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class MessageService {
	
	private SnsDao snsDao;
	
	/**
	 * 消息列表
	 */
	public ServiceResult list(Map<String, Object> para) {
		List<Message> list = snsDao.selectList("findUserMessageList", para);
		return new ServiceResult(list);
	}

	/**
	 * 未读消息数
	 */
	public ServiceResult unReadCount(Map<String, Object> para) {
		Long count = snsDao.selectOne("countUserUnReadMessage", para);
		if(count==null)
			count = 0L;
		return new ServiceResult(count);
	}

	/**
	 * 消息读取
	 */
	public ServiceResult read(Map<String, Object> para) {
		Integer messageID = (Integer)para.get("messageID");
		if(messageID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [messageID] not found!"), null);
		
		snsDao.insert("readMessage", para);
		return new ServiceResult();
	}

	/**
	 * 删除消息
	 */
	public ServiceResult del(Map<String, Object> para) {
		Integer messageID = (Integer)para.get("messageID");
		if(messageID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [messageID] not found!"), null);
		
		snsDao.insert("delMessage", para);
		return new ServiceResult();
	}

	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
}
