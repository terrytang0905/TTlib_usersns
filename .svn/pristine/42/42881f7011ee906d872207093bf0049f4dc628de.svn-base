package com.newroad.user.sns.service.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.dao.SnsDao;
import com.newroad.util.apiresult.ReturnCode;
import com.newroad.util.apiresult.ServiceResult;
import com.newroad.util.validate.ArrayValidate;
import com.newroad.util.wrapper.WrapperUtils;


/**
 * @info  : 群组
 * @author: xiangping_yu
 * @data  : 2013-10-30
 * @since : 1.5
 */
@SuppressWarnings("rawtypes")
@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRED)
public class GroupService {
	
	private static final Logger log = LoggerFactory.getLogger(GroupService.class);
	
	private static final String KEY_GROUP_STATUS = "status";
	private static final String KEY_GROUP_ID = "groupID";
	private static final String KEY_USER_ID = "userID";

	private SnsDao snsDao;
	
	/**
	 * 查找群.
	 *
	 * @param para the para ,允许参数:groupName(模糊查询),groupID(精确查询). 二条件逻辑与
	 * @return the service result
	 */
	public ServiceResult find(Map<String, Object> para) {
		ServiceResult<List<Map<String,Object>>> sr = new ServiceResult<List<Map<String,Object>>>();
		List<Map<String,Object>> groupList = snsDao.selectList("group.findGroupList",para);
		sr.setBusinessKey("group");
		sr.setBusinessResult(groupList);
		return sr;
	}
	
	/**
	 * 用户群组列表
	 */
	public ServiceResult list(Map<String, Object> para) {
		ServiceResult<List<Map<String,Object>>> sr = new ServiceResult<List<Map<String,Object>>>();
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY)) {
			return sr;
		}
		List<Map<String,Object>> groupList = snsDao.selectList("group.userGroupList",para);
		sr.setBusinessKey("group");
		sr.setBusinessResult(groupList);
		return sr;
	}
	
	/**
	 * 群组成员列表
	 */
	public ServiceResult memberList(Map<String, Object> para) {
		ServiceResult<List<Map<String,Object>>> sr = new ServiceResult<List<Map<String,Object>>>();
		if(!ServiceResult.isFullParam(para, sr, KEY_GROUP_ID)) {
			return sr;
		}
		List<Map<String,Object>> memberList = snsDao.selectList("group.groupMemberList",para);
		sr.setBusinessKey("member");
		sr.setBusinessResult(memberList);
		return sr;
	}

	/**
	 * 创建群组
	 */
	public ServiceResult create(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, "groupName", "type", "needAuth")) {
			return sr;
		}
		if(!ArrayValidate.contain(SnsConstant.GROUP_TYPE, para.get("type"))) {
			sr.setReturnCode(ReturnCode.BAD_REQUEST);
			String s = "Illegal group type:"+para.get("type");
			sr.setReturnMessage(new StringBuffer(s));
			return sr;
		}
		Integer userID = WrapperUtils.parseInteger(para.get(SnsConstant.USER_ID_KEY));
		para.put("creator", userID);
		if(para.get("icon") == null) {
			para.put("icon", SnsConstant.DEFAULT_GROUP_ICON);
		}
		snsDao.insert("group.createGroup", para);
		return sr;
	}

	/**
	 * 编辑群组信息
	 */
	public ServiceResult edit(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, KEY_GROUP_ID)) {
			return sr;
		}
		snsDao.update("group.editGroup", para);
		return sr;
	}
	
	/**
	 * 申请/邀请加入群
	 */
	@SuppressWarnings("unchecked")
	public ServiceResult request(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		String keyUser = "user";
		if(!ServiceResult.isFullParam(para, sr, KEY_GROUP_ID, keyUser)) {
			return sr;
		}
		if(!(para.get(keyUser) instanceof List)) {
			sr.setReturnCode(ReturnCode.BAD_REQUEST);
			sr.setReturnMessage(new StringBuffer("Parameter [user] must be a List."));
			return sr;
		}
		String groupID = (String)para.get(KEY_GROUP_ID);
		JSONArray reqUserList = JSONArray.fromObject(para.get(keyUser));
		for(Object row : reqUserList) {
			if(row != null && row instanceof Map) {
				Map m = (Map)row;
				if(!ArrayValidate.contain(SnsConstant.ACCOUNT_TYPE, m.get("type"))) {
					throw new IllegalArgumentException("Illegal account type:"+m.get("type"));
				}
				if(m.get("account") == null) {
					throw new RuntimeException("account is null.");
				}
				if(!ArrayValidate.contain(SnsConstant.REQUEST_DIRECTION, m.get("direction"))) {
					throw new IllegalArgumentException("Illegal direction:"+m.get("direction"));
				}
				m.put(KEY_GROUP_ID, groupID);
				snsDao.insert("group.createGroupRequest", m);
			}
		}
		
		return sr;
	}

	/**
	 * 用户申请列表查询
	 */
	public ServiceResult userAppliedList(Map<String, Object> para) {
		ServiceResult<List<Map<String,Object>>> sr = new ServiceResult<List<Map<String,Object>>>();
		if(!ServiceResult.isFullParam(para, sr, KEY_GROUP_ID)) {
			return sr;
		}
		List<Map<String,Object>> appliedList = snsDao.selectList("group.userAppliedList",para);
		sr.setBusinessKey("request");
		sr.setBusinessResult(appliedList);
		return sr;
	}
	
	/**
	 * 群组邀请列表查询
	 */
	public ServiceResult groupInviteList(Map<String, Object> para) {
		ServiceResult<List<Map<String,Object>>> sr = new ServiceResult<List<Map<String,Object>>>();
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY)) {
			return sr;
		}
		List<Map<String,Object>> invitedList = snsDao.selectList("group.groupInviteList",para);
		sr.setBusinessKey("request");
		sr.setBusinessResult(invitedList);
		return sr;
	}
	
	/**
	 * 解散群组
	 */
	public ServiceResult close(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult(); 
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, KEY_GROUP_ID)) {
			return sr;
		}
		para.put(KEY_GROUP_STATUS, SnsConstant.GROUP_STATUS_DELETE);
		return edit(para);
	}
	
	/**
	 * 发布群组公告
	 */
	public ServiceResult sendNotice(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult(); 
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, KEY_GROUP_ID)) {
			return sr;
		}
		return edit(para);
	}

	/**
	 * 审核群组申请
	 */
	public ServiceResult response(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		String keyRequestID = "requestID";
		String keyStatus = "status";
		if(!ServiceResult.isFullParam(para, sr, keyRequestID, keyStatus)) {
			return sr;
		}
		Integer status = Integer.valueOf(para.get(keyStatus).toString());
		if(!ArrayValidate.contain(SnsConstant.REQUEST_STATUS, status)) {
			sr.setReturnCode(ReturnCode.BAD_REQUEST);
			String s = "Illegal request status:"+status;
			sr.setReturnMessage(new StringBuffer(s));
			return sr;
		}
		
		snsDao.update("group.responseGroupRequest", para);
		
		//写入sns_group_user
		if(SnsConstant.REQUEST_STATUS_APPROVE == status) {
			Map<String, Object> map = new HashMap<String, Object>(3);
			snsDao.insert("group.createGroupMember", map);
		}
		return sr;
	}
	
	/**
	 * 删除群组成员
	 */
	public ServiceResult delUser(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		String keyMember = "member";
		if(!ServiceResult.isFullParam(para, sr, KEY_GROUP_ID, keyMember)) {
			return sr;
		}
		if(!(para.get(keyMember) instanceof List)) {
			sr.setReturnCode(ReturnCode.BAD_REQUEST);
			sr.setReturnMessage(new StringBuffer("Parameter ["+keyMember+"] must be a List."));
			return sr;
		}
		snsDao.delete("group.removeMember", para);
		return sr;
	}
	
	/**
	 * 编辑群成员名片
	 */
	public ServiceResult editMemberCard(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, KEY_GROUP_ID, KEY_USER_ID)) {
			return sr;
		}
		snsDao.update("group.editMemberCard", para);
		return sr;
	}
	
	/**
	 * 编辑群成员角色
	 */
	public ServiceResult editMemberRole(Map<String, Object> para) {
		ServiceResult sr = new ServiceResult();
		String keyRole = "role";
		if(!ServiceResult.isFullParam(para, sr, SnsConstant.USER_ID_KEY, KEY_GROUP_ID, KEY_USER_ID, keyRole)) {
			return sr;
		}
		snsDao.update("group.editMemberRole", para);
		return sr;
	}

	public void setSnsDao(SnsDao snsDao) {
		this.snsDao = snsDao;
	}
	
}
