package com.newroad.user.sns.service.openmessage.demo;

import com.newroad.user.sns.service.openmessage.utils.ConfigLoader;
import com.newroad.user.sns.service.openmessage.config.AppConfig;
import com.newroad.user.sns.service.openmessage.lib.MESSAGEXsend;

public class MessageXSendDemo {

  public static AppConfig config = ConfigLoader.load(ConfigLoader.ConfigType.Message);
  public static MESSAGEXsend submail = new MESSAGEXsend(config);

  /*
   * public static void main(String[] args) { AppConfig config =
   * ConfigLoader.load(ConfigLoader.ConfigType.Message); MESSAGEXsend submail = new
   * MESSAGEXsend(config); submail.addTo("18121025090"); submail.setProject("4cjge3");
   * submail.addVar("user","阿汤哥"); submail.addVar("pname","文字探密"); submail.addVar("price","499");
   * submail.addVar("pcs","1"); submail.addVar("phone", "13564367362"); submail.xsend(); }
   */

  public static void sendSystemSms(String mobileTo, String userName, String orderId, String pName, String price, String pcs,
      String userPhone) {
    submail.addTo(mobileTo);
    submail.setProject("4cjge3");
    submail.addVar("user", userName);
    submail.addVar("orderid", orderId);
    submail.addVar("pname", pName);
    submail.addVar("price", price);
    submail.addVar("pcs", pcs);
    submail.addVar("phone", userPhone);
    submail.xsend();
  }

  public static void sendClientSms(String mobileTo, String userName,String orderId, String pName, String price, String pcs) {
    submail.addTo(mobileTo);
    submail.setProject("dnVjp1");
    submail.addVar("user", userName);
    submail.addVar("orderid", orderId);
    submail.addVar("pname", pName);
    submail.addVar("price", price);
    submail.addVar("pcs", pcs);
    submail.xsend();
  }
}
