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


public class EmaySmsManager {

  private static final Logger logger = LoggerFactory.getLogger(EmaySmsManager.class);
  
  private static final String REGIST_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/regist.action";
  private static final String REGIST_INFO_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/registdetailinfo.action";
  private static final String SENDSMS_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/sendsms.action";
  private static final String LOGOUT_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/logout.action";
  private static final String BALANCE_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/querybalance.action";
  private static final String CHARGEUP_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/chargeup.action";
  private static final String CHANGEPWD_URL = EmaySmsPlatform.EMAY_HOST + "/sdkproxy/changepassword.action";
  
  private static final String SEND_TIME_URL = EmaySmsPlatform.EMAY_HOST  + "/sdkproxy/sendtimesms.action";
  private static final String RECEIVE_URL = EmaySmsPlatform.EMAY_HOST  + "/sdkproxy/getmo.action";
  
  private EmaySmsManager() {}
  
  /**
   * 亿美短信平台注册
   */
  public static boolean regist() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);

      String response = HttpUtil.httpCall(Method.get, REGIST_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
        logger.error("Emay sms platform: Regist error.", t);
    }
    return false;
  }

  /**
   * 亿美短信平台注册 (用户详细信息)
   */
  public static boolean registInfo() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);
      param.put("ename", "智牙");
      param.put("linkman", "Chris Huang");
      param.put("phonenum", "18121025090");
      param.put("mobile", "18121025090");
      param.put("email", "huangwei11@lenovo.com");
      param.put("fax", "021-99999");
      param.put("address", "Shang Hai");
      param.put("postcode", "201203");

      String response = HttpUtil.httpCall(Method.get, REGIST_INFO_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Regist info error.", t);
    }
    return false;
  }

  public static boolean sendsms(String mobile,String message) {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);
      param.put("phone", mobile);
      param.put("message", message);
      param.put("addserial", null);
      param.put("seqid", "10000000002");
      param.put("smspriority", "5");
      
      String response =HttpUtil.httpCall(Method.get, SENDSMS_URL, null, null, param).toString();
      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Send SMS error.", t);
    }
    return false;
  }
  
  
  /**
   * 注销帐号
   */
  public static boolean logOut() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);

      String response =HttpUtil.httpCall(Method.get, LOGOUT_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Logout error.", t);
    }
    return false;
  }
  
  /**
   * 查询余额
   */
  public static String balance() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);

      String response = HttpUtil.httpCall(Method.get, BALANCE_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return null;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.getMsg();
    } catch (Exception t) {
      logger.error("Emay sms platform: Query balance error.", t);
    }
    return null;
  }

  /**
   * 充值 
   */
  public static boolean chargeUp() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);
      param.put("cardno", "");
      param.put("cardpass", "");

      String response = HttpUtil.httpCall(Method.get, CHARGEUP_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Charge up error.", t);
    }
    return false;
  }

  /**
   * 修改密码
   */
  public static boolean changePWD() {
    try {
      Map<String, String> param = new HashMap<String, String>();
      param.put("cdkey", EmaySmsPlatform.cdKey);
      param.put("password", EmaySmsPlatform.password);
      param.put("newPassword", "jiejie2014jiejie");

      String response = HttpUtil.httpCall(Method.get, CHANGEPWD_URL, null, null, param).toString();

      if (StringUtils.isBlank(response)) {
        return false;
      }
      EmaySmsResponseModel rm = EmaySmsPlatform.transferNormal(response);
      return rm.success();
    } catch (Exception t) {
      logger.error("Emay sms platform: Change pass word error.", t);
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
