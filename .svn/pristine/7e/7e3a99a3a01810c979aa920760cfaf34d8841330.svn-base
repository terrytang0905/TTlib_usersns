package com.newroad.user.sns.service.friend;

import java.util.List;
import java.util.Map;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.model.friend.Attention;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;

/**
 * @info  : 关注
 * @author: xiangping_yu
 * @data  : 2013-10-29
 * @since : 1.5
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class AttentionService {
	
	private SnsDao snsDao;
	
	/**
	 * 关注列表
	 */
	public ServiceResult list(Map<String, Object> para) {
		Long userID = (Long)para.get(LoginUser.USERID);
		List<Attention> list = snsDao.selectList("attention.getAttentionUserList", userID); 
		return new ServiceResult(list);
	}
	
	/**
	 * 关注我的用户列表
	 */
	public ServiceResult attendedList(Map<String, Object> para) {
		Long userID = (Long)para.get(LoginUser.USERID);
		List<Attention> list = snsDao.selectList("attention.getAttendedList", userID); 
		return new ServiceResult(list);
	}
	
	/**
	 * 添加关注
	 */
	public ServiceResult add(Map<String, Object> para) {
		Integer attendedUserID = (Integer)para.get("attendedUserID");
		if(attendedUserID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [attendedUserID] not found!"), null);
		
		Attention attention = snsDao.selectOne("attention.getAttentionUser", para);
		if(attention!=null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("already attention!"), null);
		
		snsDao.insert("saveUserAttention", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 取消关注
	 */
	public ServiceResult cancel(Map<String, Object> para) {
		Integer attendedUserID = (Integer)para.get("attendedUserID");
		if(attendedUserID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [attendedUserID] not found!"), null);
		
		Attention attention = snsDao.selectOne("attention.getAttentionUser", para);
		if(attention==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("no attention this user!"), null);
		
		snsDao.update("cancelUserAttention", para);
		
		return new ServiceResult();
	}

	
	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
}
