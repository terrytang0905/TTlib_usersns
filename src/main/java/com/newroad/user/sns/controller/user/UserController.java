package com.newroad.user.sns.controller.user;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.filter.TokenAuthFilter;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.service.user.UserService;
import com.newroad.user.sns.util.ControllerUtils;
import com.newroad.util.apiresult.ApiReturnObjectUtil;

@Controller
@RequestMapping("/v{apiVersion}/user")
public class UserController {

  private static final Logger log = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private UserService userService;

  /**
   * 用户列表
   */
  @RequestMapping(value = "/list/{userRole}/{page}/{size}", method = RequestMethod.GET,
      produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String list(HttpServletRequest request, @PathVariable Integer userRole,
      @PathVariable Integer page, @PathVariable Integer size, @PathVariable String apiVersion)
      throws Exception {
    try {
      if (userRole <= 0) {
        userRole = null;
      }
      return userService.list(userRole, page, size).toString();
    } catch (Exception e) {
      log.error("get user info error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 检索用户信息
   */
  @RequestMapping(value = "/info/{userId}", method = RequestMethod.GET,
      produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String
      info(HttpServletRequest request, @PathVariable Long userId, @PathVariable String apiVersion)
          throws Exception {
    try {
      if (userId == null || "".equals(userId)) {
        LoginUser current = TokenAuthFilter.getCurrent();
        if (current == null) {
          return ApiReturnObjectUtil.getReturn401().toString();
        }
        log.info("User info, user login id:" + current.getUserId());
        userId = current.getUserId();
      }
      return userService.info(apiVersion, userId).toString();
    } catch (Exception e) {
      log.error("get user info error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 编辑用户信息
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST,
      produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String update(HttpServletRequest request) throws Exception {
    try {
      return userService.update(ControllerUtils.getParam(request)).toString();
    } catch (Exception e) {
      log.error("bind account error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

  /**
   * 编辑用户信息
   */
  @RequestMapping(value = "/roleupdate", method = RequestMethod.POST,
      produces = SnsConstant.CONTENT_TYPE_JSON)
  public @ResponseBody
  String updateRole(HttpServletRequest request) throws Exception {
    try {
      Map<String, Object> map = ControllerUtils.getParam(request);
      Long userId = (Long)map.get("userId");
      Integer userRole = (Integer)map.get("userRole");
      return userService.updateRole(userId, userRole).toString();
    } catch (Exception e) {
      log.error("bind account error :", e);
      return ApiReturnObjectUtil.getReturnObjFromException(e).toString();
    }
  }

}
