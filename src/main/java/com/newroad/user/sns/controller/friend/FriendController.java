package com.newroad.user.sns.controller.friend;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.service.friend.FriendGroupService;
import com.newroad.user.sns.service.friend.FriendService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/friend")
public class FriendController {

	private static final Logger log = LoggerFactory.getLogger(FriendController.class);

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private FriendGroupService friendGroupService;

	/**
	 * 好友列表
	 */
	@RequestMapping(value = "/list", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String list(HttpServletRequest request) throws Exception {
		try {
			return friendService.list(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get friend list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}

	/**
	 * 添加好友
	 */
	@RequestMapping(value = "/add", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String add(HttpServletRequest request) throws Exception {
		try {
			return friendService.add(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("add friend error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 邀请好友
	 */
	@RequestMapping(value = "/invite", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String invite(HttpServletRequest request) throws Exception {
		try {
			return friendService.invite(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("invite friend error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 好友请求列表
	 */
	@RequestMapping(value = "/applyList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String applyList(HttpServletRequest request) throws Exception {
		try {
			return friendService.applyList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get user apply list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 同意好友添加请求
	 */
	@RequestMapping(value = "/agree", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String agree(HttpServletRequest request) throws Exception {
		try {
			return friendService.agree(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("agree friend apply error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 拒绝好友添加请求
	 */
	@RequestMapping(value = "/reject", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String reject(HttpServletRequest request) throws Exception {
		try {
			return friendService.reject(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("reject friend apply error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 删除好友
	 */
	@RequestMapping(value = "/del", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String del(HttpServletRequest request) throws Exception {
		try {
			return friendService.del(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("delte friend error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 黑名单列表
	 */
	@RequestMapping(value = "/blackList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String blackList(HttpServletRequest request) throws Exception {
		try {
			return friendService.blackList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get black list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 加黑名单
	 */
	@RequestMapping(value = "/addBlackList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String addBlackList(HttpServletRequest request) throws Exception {
		try {
			return friendService.addBlackList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("add black list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}	
	
	/**
	 * 移除黑名单
	 */
	@RequestMapping(value = "/delBlackList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String delBlackList(HttpServletRequest request) throws Exception {
		try {
			return friendService.delBlackList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("delete black list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 好友分组列表
	 */
	@RequestMapping(value = "/groupList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String groupList(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.list(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get friend group list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 好友分组详细信息
	 */
	@RequestMapping(value = "/groupInfo", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String groupInfo(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.info(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get friend group info error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 添加好友分组
	 */
	@RequestMapping(value = "/addGroup", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String addGroup(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.add(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("add friend group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 编辑好友分组
	 */
	@RequestMapping(value = "/editGroup", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String editGroup(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.edit(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("edit friend group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 删除好友分组
	 */
	@RequestMapping(value = "/delGroup", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String delGroup(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.del(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("delete friend group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 分组好友
	 */
	@RequestMapping(value = "/group", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String group(HttpServletRequest request) throws Exception {
		try {
			return friendGroupService.group(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("group friend group error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
}
