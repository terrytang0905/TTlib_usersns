package com.newroad.user.sns.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newroad.user.sns.constant.SnsConstant;
import com.newroad.user.sns.filter.HttpMutualGetFilter;
import com.newroad.user.sns.filter.TokenAuthFilter;
import com.newroad.user.sns.model.login.LoginUser;
import com.newroad.util.auth.TokenUtil;

/**
 * @info : 控制器操作帮助类
 * @author: tangzj
 * @data : 2013-11-06
 * @since : 1.5
 */
public class ControllerUtils {

  private ControllerUtils() {}

  private static final Logger log = LoggerFactory.getLogger(ControllerUtils.class);

  @SuppressWarnings("unchecked")
  public static Map<String, Object> getParam(HttpServletRequest request) {
    StringBuilder sb = new StringBuilder();
    // $1 from inputstream
    BufferedReader br = null;
    try {
      br = new BufferedReader(new InputStreamReader((ServletInputStream) request.getInputStream(), "UTF-8"));
      String line = null;
      while ((line = br.readLine()) != null) {
        sb.append(line);
      }
    } catch (Exception e) {
      log.error("read parameter from request inputstrem error!", e);
    } finally {
      try {
        br.close();
      } catch (Exception e) {
        // ignore close exception
      }
    }

    // $2 from param
    if (StringUtils.isBlank(sb.toString())) {
      Map<String, Object> requestParams = request.getParameterMap();
      Map<String, Object> params = new HashMap<String, Object>(requestParams.size());

      for (Entry<String, Object> entry : requestParams.entrySet()) {

        String name = (String) entry.getKey();
        String[] values = (String[]) entry.getValue();
        String valueStr = "";
        for (int i = 0; i < values.length; i++) {
          valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
        }
        if (valueStr.length() > 0)
          params.put(name, valueStr);
      }
      // for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
      // String name = (String) iter.next();
      // String[] values = (String[]) requestParams.get(name);
      // String valueStr = "";
      // for (int i = 0; i < values.length; i++) {
      // valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
      // }
      // if(valueStr.length() > 0)
      // params.put(name, valueStr);
      // }

      LoginUser login = TokenAuthFilter.getCurrent();
      if (login != null) {
        params.put(LoginUser.USERID, login.getUserId());
        params.put(TokenUtil.TOKEN, login.getToken());
      }
      return params;
    }

    try {
      JSONObject json = JSONObject.fromObject(sb.toString());
      Map<String, Object> params = (Map<String, Object>) JSONObject.toBean(json, Map.class);

      LoginUser login = TokenAuthFilter.getCurrent();
      if (login != null) {
        params.put(LoginUser.USERID, login.getUserId());
        params.put(TokenUtil.TOKEN, login.getToken());
      }
      return params;
    } catch (Exception e) {
      log.error("transform parameter from request inputstrem error!", e);
    }
    return MapUtils.EMPTY_MAP;
  }

  /**
   * 取客户端IP
   */
  public static String getIP(HttpServletRequest request) {
    return request.getLocalAddr();
  }

  /**
   * 取HTTP头
   */
  public static Map<String, String> getHttpHeaders() {
    String data = HttpMutualGetFilter.getRequestHeader(SnsConstant.COLLECT_HEADER_KEY);
    String token = HttpMutualGetFilter.getRequestHeader(TokenUtil.TOKEN);

    Map<String, String> header = new HashMap<String, String>(1);
    header.put(SnsConstant.COLLECT_HEADER_KEY, data);
    header.put(TokenUtil.TOKEN, token);
    return header;
  }

  public static StringBuffer bulid(String text) {
    StringBuffer buff = new StringBuffer();
    buff.append(text);
    return buff;
  }
}
