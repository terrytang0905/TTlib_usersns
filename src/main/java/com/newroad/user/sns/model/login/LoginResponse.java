package com.newroad.user.sns.model.login;

import java.io.Serializable;

public class LoginResponse {
	/**
	 * 成功状态
	 */
	public boolean success;
	/**
	 * 登录信息
	 */
	public LoginInfo loginInfo;
	/**
	 * 登录业务数据
	 */
	public LoginData loginData;
	
	public static LoginResponse fromSuccess(LoginInfo loginInfo, LoginData loginData) {
		LoginResponse response = new LoginResponse();
		response.setSuccess(true);
		response.setLoginInfo(loginInfo);
		response.setLoginData(loginData);
		return response;
	}
	
	public static LoginResponse fromError(LoginInfo loginInfo) {
		LoginResponse response = new LoginResponse();
		response.setSuccess(false);
		response.setLoginInfo(loginInfo);
		return response;
	}
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public LoginInfo getLoginInfo() {
		return loginInfo;
	}
	public void setLoginInfo(LoginInfo loginInfo) {
		this.loginInfo = loginInfo;
	}
	public LoginData getLoginData() {
		return loginData;
	}
	public void setLoginData(LoginData loginData) {
		this.loginData = loginData;
	}
	
	public static interface LoginInfo {}
	public static interface LoginData extends Serializable {}
}
