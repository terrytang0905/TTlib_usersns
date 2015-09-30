package com.newroad.user.sns.service.application;

import java.util.Map;

import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.login.LoginResponse;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.model.user.User;
import com.newroad.user.sns.service.application.Application.APP;

/**
 * @info  : 应用系统服务 
 * @author: xiangping_yu
 * @data  : 2013-11-6
 * @since : 1.5
 */
public class ApplicationService {
	
	private Map<APP, Application> applications;

	/**
	 * 应用系统登录
	 */
	public LoginResponse login(String app, User user, LoginContext context, String apiVersion) {
		APP _app = APP.fromKey(app);
		return applications.get(_app).login(user, context, apiVersion);
	}
	
	/**
	 * 取应用系统用户信息
	 */
	public Object getUserInfo(String app, User user, LoginUser current, String apiVersion) {
		APP _app = APP.fromKey(app);
		return applications.get(_app).getUserInfo(user, current, apiVersion);
	}
	
	public Object logout(String app, LoginUser user, String apiVersion) {
		APP _app = APP.fromKey(app);
		return applications.get(_app).logout(user, apiVersion);
	}
	
	public void setApplications(Map<APP, Application> applications) {
		this.applications = applications;
	}
}
