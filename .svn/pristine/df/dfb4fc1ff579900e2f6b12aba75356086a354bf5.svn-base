package com.newroad.user.sns.service.friend;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.newroad.user.sns.dao.SnsDao;
import com.newroad.user.sns.model.friend.Friend;
import com.newroad.user.sns.model.friend.FriendGroup;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;
import com.newroad.util.cosure.CosureUtils;

/**
 * @info  : 好友分组 
 * @author: xiangping_yu
 * @data  : 2013-10-29
 * @since : 1.5
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class FriendGroupService {
	
	private SnsDao snsDao;
	
	/**
	 * 好友分组列表
	 */
	public ServiceResult list(Map<String, Object> para) {
		Long userID = (Long)para.get(LoginUser.USERID);
		List<FriendGroup> list = snsDao.selectList("friend.findUserFriendGroupList", userID);
		return new ServiceResult("list", list);
	}
	
	/**
	 * 好友分组信息
	 */
	public ServiceResult info(Map<String, Object> para) {
		Integer friendGroupID = (Integer)para.get("friendGroupID");
		if(friendGroupID == null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendGroupID] not found!"), null);
		
		FriendGroup group = snsDao.selectOne("friend.getUserFriendGroup", para);
		if(group==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend group not found!"), null);
		
		// 分组好友
		List<Friend> friends = snsDao.selectList("friend.findFriendGroupUserList", para);
		group.setFriends(friends);
		return new ServiceResult(group);
	}
	
	/**
	 * 添加好友分组
	 */
	public ServiceResult add(Map<String, Object> para) {
		Long userID = (Long)para.get(LoginUser.USERID);
		String name = (String)para.get("name");
		if(StringUtils.isBlank(name))
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [name] not found!"), null);
		
		List<FriendGroup> groups = snsDao.selectList("friend.getUserFriendGroupByName", name);
		if(!CollectionUtils.isEmpty(groups))
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend group already exist!"), null);
		
		FriendGroup group = new FriendGroup();
		group.setUserID(userID);
		group.setName(name);
		snsDao.insert("friend.saveFriendGroup", group);
		
		List<Integer> friends = (List<Integer>)para.get("friends");
		if(!CollectionUtils.isEmpty(friends)) {
			Long id = group.getFriendGroupID();
			para.put("friendGroupID", id);
			for(Integer fid : friends) {
				para.put("friendID", fid);
				
				Friend friend = snsDao.selectOne("friend.getUserFriendByID", (long)fid);
				if(friend!=null)
					snsDao.insert("friend.saveFriendGroupUser", para);
			}
		}
		
		return new ServiceResult();
	}
	
	/**
	 * 编辑好友分组
	 */
	public ServiceResult edit(Map<String, Object> para) {
		Integer friendGroupID = (Integer)para.get("friendGroupID");
		if(friendGroupID == null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendGroupID] not found!"), null);
		
		String name = (String)para.get("name");
		if(StringUtils.isBlank(name))
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [name] not found!"), null);
		
		FriendGroup group = snsDao.selectOne("friend.getUserFriendGroup", para);
		if(group==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend group not found!"), null);
		
		snsDao.update("friend.updateFriendGroup", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 删除好友分组
	 */
	public ServiceResult del(Map<String, Object> para) {
		Integer friendGroupID = (Integer)para.get("friendGroupID");
		if(friendGroupID == null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendGroupID] not found!"), null);
		
		FriendGroup group = snsDao.selectOne("friend.getUserFriendGroup", para);
		if(group==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend group not found!"), null);
		
		snsDao.update("friend.deleteFriendGroup", para);
		
		snsDao.update("friend.deleteFriendGroupUser", para);
		
		return new ServiceResult();
	}
	
	/**
	 * 分组好友
	 */
	public ServiceResult group(Map<String, Object> para) {
		Integer friendGroupID = (Integer)para.get("friendGroupID");
		
		List<Integer> friends = (List<Integer>)para.get("friends");
		if(friendGroupID == null)
			return new ServiceResult(ReturnCode.BAD_REQUEST, ControllerUtils.bulid("parameter [friendGroupID] not found!"), null);
		
		FriendGroup group = snsDao.selectOne("friend.getUserFriendGroup", para);
		if(group==null)
			return new ServiceResult(ReturnCode.ILLEGAL_DATA, ControllerUtils.bulid("friend group not found!"), null);
		
		// 分组好友
		List<Friend> list = snsDao.selectList("friend.findFriendGroupUserList", para);
		
		if(CollectionUtils.isEmpty(list) && CollectionUtils.isEmpty(friends))
			return new ServiceResult();
		
		// 删除分组好友
		if(CollectionUtils.isEmpty(friends)) {
			para.put("friends", CosureUtils.convert(list, Friend.TO_FRIENDID));
			snsDao.update("friend.batchDeleteFriendGroupUser", para);
		}
		
		if(CollectionUtils.isEmpty(list)) {
			for(Integer fid : friends) {
				para.put("friendID", fid);
				
				Friend friend = snsDao.selectOne("friend.getUserFriendByID", (long)fid);
				if(friend!=null)
					snsDao.insert("friend.saveFriendGroupUser", para);
			}
		} else {
			List<Long> friendIds = CosureUtils.convert(list, Friend.TO_FRIENDID);
			List<Long> exist = new ArrayList<Long>(friendIds.size());
			for(Integer fid : friends) {
				if(friendIds.contains((long)fid)){
					exist.add((long)fid);
				} else {
					para.put("friendID", fid);
					
					Friend friend = snsDao.selectOne("friend.getUserFriendByID", (long)fid);
					if(friend!=null)
						snsDao.insert("friend.saveFriendGroupUser", para);
				}
			}
			
			if(friendIds.size()!=exist.size()) {
				friendIds.removeAll(exist);
				para.put("friends", friendIds);
				snsDao.update("friend.batchDeleteFriendGroupUser", para);
			}
		}
		return new ServiceResult();
	}

	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
}
