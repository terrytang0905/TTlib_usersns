package com.newroad.user.sns.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;

/**
 * @info  : 用于在 service层 获取会话信息  request, response 
 * @author: xiangping_yu
 * @data  : 2013-5-29
 * @since : 1.5
 */
public class HttpMutualGetFilter implements Filter {
	
	static ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	static ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
	    res.setDateHeader("Expires", 0);
	    res.setHeader("Cache-Control","no-store, max-age=0, no-cache, must-revalidate");    
	    res.addHeader("Cache-Control", "post-check=0, pre-check=0");
	    res.setHeader("Pragma", "no-cache");
        
		requestLocal.set((HttpServletRequest) request);
		responseLocal.set((HttpServletResponse) response);
		try {
			chain.doFilter(request, response);
		} finally {
			requestLocal.remove();
			responseLocal.remove();
		}
	}
	
	/**
	 * info  : 获得request
	 * autor : xiangping_yu
	 */
	static public HttpServletRequest getReq(){
		return requestLocal.get();
	}
	
	/**
	 * info  : 获得response
	 * autor : xiangping_yu
	 */
	static public HttpServletResponse getRes(){
		return responseLocal.get();
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void destroy() {
		requestLocal.remove();
		responseLocal.remove();
	}
	
	@SuppressWarnings("unchecked")
	public static Map<String, String> getHttpHeaders() {
		HttpServletRequest request = requestLocal.get();
		if(request==null) return MapUtils.EMPTY_MAP;
		
		Enumeration<String> headerNames = request.getHeaderNames();
		Map<String, String> headers = new HashMap<String, String>();
		String name = null;
        while (headerNames != null && headerNames.hasMoreElements()) {
        	name = headerNames.nextElement();
        	if (name != null) {
        		headers.put(name, request.getHeader(name));
        	}
        }
		return headers;
	}
	
	/**
	 * 从request头中获得指定key的值
	 * @param headName
	 * @return
	 */
	public static String getRequestHeader(String headName) {
		if(StringUtils.isBlank(headName) || requestLocal.get() == null) {
			return null;
		}
		return requestLocal.get().getHeader(headName);
	}
	
}
