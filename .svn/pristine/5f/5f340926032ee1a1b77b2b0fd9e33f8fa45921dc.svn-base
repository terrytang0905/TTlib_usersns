package com.newroad.user.sns.controller.friend;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.service.friend.AttentionService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/attention")
public class AttentionController {

	private static final Logger log = LoggerFactory.getLogger(AttentionController.class);

	@Autowired
	private AttentionService attentionService;
	
	/**
	 * 关注列表
	 */
	@RequestMapping(value = "/list", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String list(HttpServletRequest request) throws Exception {
		try {
			return attentionService.list(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get attention list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 关注我的用户列表
	 */
	@RequestMapping(value = "/attendedList", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String attendedList(HttpServletRequest request) throws Exception {
		try {
			return attentionService.attendedList(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get attented list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}

	/**
	 * 添加关注
	 */
	@RequestMapping(value = "/add", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String add(HttpServletRequest request) throws Exception {
		try {
			return attentionService.add(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("add user attention error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 取消关注
	 */
	@RequestMapping(value = "/cancel", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String cancel(HttpServletRequest request) throws Exception {
		try {
			return attentionService.cancel(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("cancel user attention error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
}
