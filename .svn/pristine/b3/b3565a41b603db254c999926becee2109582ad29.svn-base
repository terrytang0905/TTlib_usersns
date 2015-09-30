package com.newroad.user.sns.service.friend;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.event.Event;
import com.newroad.user.sns.model.Page;
import com.newroad.user.sns.model.friend.BlackUser;
import com.newroad.user.sns.model.friend.Friend;
import com.newroad.user.sns.model.friend.FriendApply;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.model.user.User;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;

/**
 * @info  : 好友 
 * @author: xiangping_yu
 * @data  : 2013-10-29
 * @since : 1.5
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FriendService {
	
	private SnsDao snsDao;
	private Event afterInvite;
	
	/**
	 * 好友列表
	 */
	public ServiceResult list(Map<String, Object> para) {
		Page<List<Friend>> page = new Page<List<Friend>>();
		page.getPara().putAll(para);
		
		page.setPage(para.get(SnsConstant.PAGE_KEY));
		page.setSize(para.get(SnsConstant.PAGE_SIZE_KEY));
		
		page = snsDao.selectPage("friend.findUserFriendList", page);
		
		return new ServiceResult<Object>(ControllerUtils.fromPage(page));
	}
	
	/**
	 * 添加好友
	 */
	public ServiceResult add(Map<String, Object> para) {
		Integer friendID = (Integer)para.get("friendID");
		Long userID = (Long)para.get(LoginUser.USERID);
		if(friendID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendID] not found!"), null);
		
		if((long)friendID == (long)userID)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("can not add yourself as a friend!"), null);
		
		User user = (User)snsDao.selectOne("user.getUserInfoByUserID", userID);
		if(user==null) 
			return new ServiceResult(ReturnCode.DATA_NOT_FOUND, ControllerUtils.bulid("apply user not found!"), null);
		
		Friend friend = snsDao.selectOne("friend.getUserFriend", para);
		if(friend!=null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("you are already friends!"), null);
		
		FriendApply apply = snsDao.selectOne("friend.getFriendApply", para);
		if(apply!=null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("already send apply!"), null);
		
		snsDao.insert("friend.saveUserFriendApply", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 邀请好友
	 */
	public ServiceResult invite(Map<String, Object> para) {
		Integer type = (Integer)para.get("type");
		if(type==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [type] not found!"), null);
		
		String account = (String)para.get("account");
		if(StringUtils.isBlank(account))
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [account] not found!"), null);
		
		FriendApply invite = (FriendApply)snsDao.selectOne("friend.getFriendInvite", para);
		if(invite!=null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("already send invite!"), null);
		
		snsDao.insert("friend.saveFriendInvite", para);
		
		afterInvite.doEvent(para);
		
		return new ServiceResult();
	}
	
	/**
	 * 好友请求列表
	 */
	public ServiceResult applyList(Map<String, Object> para) {
		Page<List<FriendApply>> page = new Page<List<FriendApply>>();
		page.getPara().putAll(para);
		
		page.setPage(para.get(SnsConstant.PAGE_KEY));
		page.setSize(para.get(SnsConstant.PAGE_SIZE_KEY));
		
		page = snsDao.selectPage("friend.findUserApplyList", page);
		
		return new ServiceResult<Object>(ControllerUtils.fromPage(page));
	}
	
	/**
	 * 同意好友添加请求
	 */
	public ServiceResult agree(Map<String, Object> para) {
		Integer applyID = (Integer)para.get("applyID");
		Long userID = (Long)para.get(LoginUser.USERID);
		if(applyID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [applyID] not found!"), null);

		FriendApply apply = (FriendApply)snsDao.selectOne("friend.getFriendApplyByID", (long)applyID);
		if(apply==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend apply not found!"), null);
		
		if((long)userID!= (long)Long.parseLong(apply.getAccount()))
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("can not handle this apply!"), null);
		
		if(apply.getStatus()!=0)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("already handle this apply!"), null);
		
		snsDao.update("friend.agreeFriendApply", para);
		
		para.put("friendID", apply.getUserID());
		snsDao.insert("friend.addUserFriend", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 拒绝好友添加请求
	 */
	public ServiceResult reject(Map<String, Object> para) {
		Integer applyID = (Integer)para.get("applyID");
		if(applyID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [applyID] not found!"), null);

		FriendApply apply = (FriendApply)snsDao.selectOne("friend.getFriendApplyByID", (long)applyID);
		if(apply==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend apply not found!"), null);
		
		snsDao.update("friend.rejectFriendApply", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 删除好友
	 */
	public ServiceResult del(Map<String, Object> para) {
		Integer friendID = (Integer)para.get("friendID");
		if(friendID==null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendID] not found!"), null);
		
		Friend friend = snsDao.selectOne("friend.getUserFriend", para);
		if(friend==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("apply user not found!"), null);
		
		snsDao.update("friend.deleteUserFriend", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 黑名单列表
	 */
	public ServiceResult blackList(Map<String, Object> para){
		List<BlackUser> list = snsDao.selectList("friend.findUserBlackListByUserID", para);
		
		return new ServiceResult(list);
	}
	
	/**
	 * 加黑名单
	 */
	public ServiceResult addBlackList(Map<String, Object> para){
		String friendID = (String)para.get("friendID");
		if(StringUtils.isBlank(friendID))
			return new ServiceResult();
		
		BlackUser black = (BlackUser)snsDao.selectOne("friend.getBlackUserByID", friendID);
		if(black!=null)
			return new ServiceResult();
		
		User user = (User)snsDao.selectOne("friend.getUserInfoByUserID", friendID);
		if(user==null)
			return new ServiceResult();
		
		snsDao.insert("friend.saveUserBlack", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 移除黑名单
	 */
	public ServiceResult delBlackList(Map<String, Object> para) {
		String friendID = (String)para.get("friendID");
		if(StringUtils.isBlank(friendID))
			return new ServiceResult();
		
		BlackUser black = (BlackUser)snsDao.selectOne("friend.getBlackUserByID", friendID);
		if(black==null)
			return new ServiceResult();
		
		snsDao.update("friend.deleteUserBlack", para);
		
		return new ServiceResult();
	}

	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
	public void setAfterInvite(Event afterInvite) {
		this.afterInvite = afterInvite;
	}
}
