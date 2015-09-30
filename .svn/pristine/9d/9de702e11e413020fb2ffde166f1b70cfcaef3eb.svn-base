package com.newroad.user.sns.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.service.message.sms.MessageConstant;
import com.newroad.user.sns.service.user.AuthService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/auth")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private AuthService authService;

  /**
   * 登陆认证
   */
  @RequestMapping(value = "/login", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String login(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    try {
      return authService.login(apiVersion, ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("login failure error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 登出
   */
  @RequestMapping(value = "/logout", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String logout(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    try {
      log.info("logout request parameter:" + request);
      return authService.logout(apiVersion).toString();
    } catch (Exception e) {
      log.error("logout failure error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 登出
   */
  @RequestMapping(value = "/sms", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String sms(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    try {
      return authService.smsVerificationCode(apiVersion, ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("mobile sms error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }


  @RequestMapping(value = "/sms/common", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String smsMessage(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    Map<String, Object> requestMap = ControllerUtils.getParam(request);
    try {
      String mobile = (String) requestMap.get(MessageConstant.MOBILE);
      String message = (String) requestMap.get(MessageConstant.MESSAGE);
      return authService.smsMessage(apiVersion, mobile, message).toString();
    } catch (Exception e) {
      log.error("mobile sms error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 登陆认证
   */
  @RequestMapping(value = "/register", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String register(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    try {
      return authService.register(apiVersion, ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("register user error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 验证Token用户基本信息有效
   */
  @RequestMapping(value = "/check", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String check(HttpServletRequest request) throws Exception {
    try {
      return authService.check(ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("check token user login error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 
   */
  @RequestMapping(value = "/resetpwd", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String resetPassword(HttpServletRequest request) throws Exception {
    try {
      return authService.resetPassword(ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("resetpwd user login error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 验证用户基本信息有效
   */
  @RequestMapping(value = "/userexist", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String userexist(HttpServletRequest request, @PathVariable String apiVersion) throws Exception {
    try {
      return authService.checkUserExist(apiVersion, ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("check user exist error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 用户请求安全认证
   */
  @RequestMapping(value = "/security", produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String security(HttpServletRequest request) throws Exception {
    try {
      return authService.security(ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("user security error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }
}
