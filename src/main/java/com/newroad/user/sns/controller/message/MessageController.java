package com.newroad.user.sns.controller.message;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.service.message.MessageService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/message")
public class MessageController {

	private static final Logger log = LoggerFactory.getLogger(MessageController.class);

	@Autowired
	private MessageService messageService;
	
	/**
	 * 消息列表
	 */
	@RequestMapping(value = "/list", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String list(HttpServletRequest request) throws Exception {
		try {
			return messageService.list(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get message list error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 未读消息数
	 */
	@RequestMapping(value = "/unReadCount", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String unReadCount(HttpServletRequest request) throws Exception {
		try {
			return messageService.unReadCount(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("get unread message count error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 消息读取
	 */
	@RequestMapping(value = "/read", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String read(HttpServletRequest request) throws Exception {
		try {
			return messageService.read(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("read message error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
	
	/**
	 * 删除消息
	 */
	@RequestMapping(value = "/del", produces = SnsConstant.CONTENT_TYPE_JSON)
	public @ResponseBody String del(HttpServletRequest request) throws Exception {
		try {
			return messageService.del(ControllerUtils.getParam(request)).toString();
		} catch (Exception e) {
			log.error("delete message error :", e);
			return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
		}
	}
}
