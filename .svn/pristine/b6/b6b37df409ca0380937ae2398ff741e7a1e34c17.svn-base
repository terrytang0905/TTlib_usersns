package com.newroad.user.sns.service.message.sms;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newroad.user.sns.service.message.sms.EmaySmsResponseModel.EmaySmsReceiveResponseModel;
import com.newroad.user.sns.util.HttpUtil;
import com.newroad.user.sns.util.HttpUtil.Method;

/**
 * Emay sms platform
 * 
 * @author: xiangping_yu
 * @data : 2014-4-3
 * @since : 1.5
 */
public class EmaySmsPlatform {
  
  private static final Logger logger = LoggerFactory.getLogger(EmaySmsPlatform.class);
  
  static final String EMAY_HOST = "http://sdk229ws.eucp.b2m.cn:8080";
  static final String cdKey = "9SDK-EMY-0229-JDQUO";
  static final String password = "096336";
  
  private static final String SEND_URL = EMAY_HOST + "/sdkproxy/sendsms.action";
  private static final String SEND_TIME_URL = EMAY_HOST + "/sdkproxy/sendtimesms.action";
  private static final String RECEIVE_URL = EMAY_HOST + "/sdkproxy/getmo.action";
  
  private EmaySmsPlatform() {}
  
  public static void main(String[] args) {
    System.out.println(sendSms("15692120366", "【借借】您好，您申请修改密码，本次验证码：%s，有效期5分钟。"));
  }
  
  /**
   * 发送短信 
   * @param phone 多个手机号用","隔开
   */
  public static boolean sendSms(String phone, String message) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", cdKey);
      param.put("password", password);
      param.put("phone", phone);
      param.put("message", message);
      param.put("addserial", null);
      param.put("seqid", "10000000002");
      param.put("smspriority", "5");
      //param.put("sendtime", null);
      
      String response = HttpUtil.httpCall(Method.get, SEND_URL, null, null, param).toString();
      if (StringUtils.isBlank(response)) {
        return false;
      }
      
      System.out.println(response);
      
      EmaySmsResponseModel rm = transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Send sms error.", t);
    }
    return false;
  }

  /**
   * 发送定时短信 TODO 参数定义
   */
  public static boolean sendTimeSms(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());
      param.put("phone", "");
      param.put("message", "");
      param.put("sendtime", "");
      param.put("addserial", "");

      String response = HttpUtil.httpCall(Method.post, SEND_TIME_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      transferNormal(response);
    } catch (Exception t) {
      logger.error("Emay sms platform: Send time sms error.", t);
    }
    return false;
  }

  /**
   * 接收短信
   */
  public static boolean receive(EmaySmsConfig sms) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", sms.getCdKey());
      param.put("password", sms.getPassWord());

      String response = HttpUtil.httpCall(Method.post, RECEIVE_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      transferReceive(response);
    } catch (Exception t) {
      logger.error("Emay sms platform: Receive sms error.", t);
    }
    return false;
  }

  protected static EmaySmsResponseModel transferNormal(String response) throws Exception {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new ByteArrayInputStream(response.trim().getBytes()));
    
    Element root = doc.getRootElement();
    Element code = root.element("error");
    Element message = root.element("message");
    
    EmaySmsResponseModel model = new EmaySmsResponseModel();
    model.setCode(code==null ? -1 : Integer.parseInt(code.getText()));
    model.setMsg(message==null ? null : message.getText());
    return model;
  }

  @SuppressWarnings("unchecked")
  private static EmaySmsReceiveResponseModel transferReceive(String response) throws Exception {
    SAXReader reader = new SAXReader();
    Document doc = reader.read(new ByteArrayInputStream(response.trim().getBytes()));
    Element rootElement = doc.getRootElement();
    for (Element element : (List<Element>) rootElement.elements()) {
      element.getName();
    }

    EmaySmsReceiveResponseModel model = new EmaySmsReceiveResponseModel();
    return model;
  }

}
