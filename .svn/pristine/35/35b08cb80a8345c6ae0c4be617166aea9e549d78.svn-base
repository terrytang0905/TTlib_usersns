package com.newroad.user.sns.controller.group;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.service.group.GroupService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/group")
public class GroupController {

	private static final Logger log = LoggerFactory.getLogger(GroupController.class);

	@Autowired
	private GroupService groupService;
	
	/**
	 * 查找群组
	 */
	@RequestMapping(value = "/find", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String find(HttpServletRequest request) throws Exception {
		try {
			return groupService.find(ControllerUtils.getParam(request)).toString();
			
		} catch (Exception e) {
			log.error("get group list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 群组列表
	 */
	@RequestMapping(value = "/list", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String list(HttpServletRequest request) throws Exception {
		try {
			return groupService.list(ControllerUtils.getParam(request)).toString();
			
		} catch (Exception e) {
			log.error("get group list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 群组成员列表
	 */
	@RequestMapping(value = "/memberList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String memberList(HttpServletRequest request) throws Exception {
		try {
			return groupService.memberList(ControllerUtils.getParam(request)).toString();
			
		} catch (Exception e) {
			log.error("get group list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 创建群组
	 */
	@RequestMapping(value = "/create", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String create(HttpServletRequest request) throws Exception {
		try {
			return groupService.create(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("create group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 申请/邀请加入群
	 */
	@RequestMapping(value = "/request", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String request(HttpServletRequest request) throws Exception {
		try {
			return groupService.request(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("create group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 用户申请列表查询
	 */
	@RequestMapping(value = "/userAppliedList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String userAppliedList(HttpServletRequest request) throws Exception {
		try {
			return groupService.userAppliedList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("create group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 用户申请列表查询
	 */
	@RequestMapping(value = "/groupInviteList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String groupInviteList(HttpServletRequest request) throws Exception {
		try {
			return groupService.groupInviteList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("create group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}

	/**
	 * 编辑群组信息
	 */
	@RequestMapping(value = "/edit", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String edit(HttpServletRequest request) throws Exception {
		try {
			return groupService.edit(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("edit group info error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 解散群组
	 */
	@RequestMapping(value = "/close", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String close(HttpServletRequest request) throws Exception {
		try {
			return groupService.close(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("close group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 发布群组公告
	 */
	@RequestMapping(value = "/sendNotice", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String sendNotice(HttpServletRequest request) throws Exception {
		try {
			return groupService.edit(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("send group notice error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 审核群组申请
	 */
	@RequestMapping(value = "/response", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String response(HttpServletRequest request) throws Exception {
		try {
			return groupService.response(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("approval group apply error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 删除群组成员
	 */
	@RequestMapping(value = "/delUser", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String delUser(HttpServletRequest request) throws Exception {
		try {
			return groupService.delUser(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("delete group user error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
}
