package com.newroad.user.sns;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import com.newroad.user.sns.util.HttpUtil;
import com.newroad.user.sns.util.HttpUtil.Method;

public class AuthTest {
	
	static final String URL = "http://127.0.0.1:8080/supernote/v1/auth/loginSt.login";
	static final String REALM = "supernote.lenovo.com";
	
	public static void main(String[] args) {
		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
//		new Thread(new Thread1()).start();
//		new Thread(new Thread2()).start();
	}
	
	public static void main1(String[] args) {
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1383300666337L)));
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1385627863268L)));
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1383730021650L)));
		
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(1384430815671L)));		
	}
	
	static void post(String thread, String lpsust) {
		try {
			JSONObject json = new JSONObject();
			json.put("LenovoToken", lpsust);
			json.put("realm", REALM);
			
			StringBuffer sb = HttpUtil.httpCall(Method.post, URL, null, new StringBuffer(json.toString()), null);
			System.out.println(thread+">>>>>>>>>>>>>>>"+sb);
		} catch (Exception e) {
			System.out.println(thread+" ERROR:"+e.getMessage());
		}
	}
}

/**
 * yuxiangping21@163.com
 */
class Thread1 implements Runnable{
	public void run() {
		String lpsust = "ZAgAAAAAAAGE9MTAwMTEzNjMxMzMmYj0xJmM9MSZkPTEwNjQyJmU9RjA1MDMxQjhDQkNCQUMwOEIwNzJBNEU5RTg1MTUxRjExJmg9MTM4NjAzNTM5MzYwOSZpPTQzMjAwE8lMo1G805CoSjZyXi7YUQ==";
		for(int i=0; i<10; i++) {
			AuthTest.post("THREAD-"+i, lpsust);
		}
	}
}

/**
 * eason.xp.yu@gmail.com
 */
class Thread3 implements Runnable{
	public void run() {
		String lpsust = "ZAgAAAAAAAGE9MTAwMTI3Nzc1OTUmYj0xJmM9MSZkPTEwNjQyJmU9RjNCRDIxQ0VGQThGMjNFMjk5NUE1OEVEMEUyNkFCMzMxJmg9MTM4NTk1MTg3MDU3NCZpPTQzMjAwJmo9MTM4NTk1MTg3MEabt5cvRroTqo1c3bfVa1w=";
		for(int i=0; i<100; i++) {
			AuthTest.post("THREAD-2", lpsust);
		}
	}
}

/**
 * tang
 */
class Thread2 implements Runnable{
	public void run() {
		String lpsust = "ZAgAAAAAAAGE9MTAwMTQyMjIyOTUmYj0xJmM9MSZkPTEwNjQyJmU9RTc5NThCQzhCMkYxREUxRThENzMyOUY1NjE0OTE2N0YxJmg9MTM4NTk1MTgyNjg3NiZpPTQzMjAwJmo9MTM4NTk1MTgyNrhHC0iJ1YteAcf14-ZactE=";
		for(int i=0; i<100; i++) {
			AuthTest.post("THREAD-3", lpsust);
		}
	}
}

