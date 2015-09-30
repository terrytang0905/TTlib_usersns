package com.newroad.user.sns.service.application;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newroad.user.sns.model.login.LoginContext;
import com.newroad.user.sns.model.login.LoginResponse;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.user.sns.model.user.User;

/**
 * @info  : 应用系统接口 
 * @author: xiangping_yu
 * @data  : 2013-10-30
 * @since : 1.5
 */
public interface Application {
	
	Logger log = LoggerFactory.getLogger(Application.class);
	
	/**
	 * 应用系统的用户登录信息
	 */
	LoginResponse login(User user, LoginContext context, String apiVersion);
	
	/**
	 * 获取应用系统的用户信息
	 */
	Object getUserInfo(User user, LoginUser current, String apiVersion);
	
	/**
	 * 应用系统的用户登出
	 */
	Object logout(LoginUser user, String apiVersion);
	
	public static enum APP {
		supernote("supernote", "乐云记事"),
		greentea("greentea", "绿茶");
		
		private String key;
		private String name;
		
		private APP(String key, String name) {
			this.key = key;
			this.name = name;
		}
		
		public static APP fromKey(String key) {
			if(StringUtils.isBlank(key))
				return APP.supernote;
			
			for(APP app : values()) {
				if(app.getKey().equals(key))
					return app;
			}
			
			return APP.supernote;
		}

		public String getKey() {
			return key;
		}
		public String getName() {
			return name;
		}
	}
}
