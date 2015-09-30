package com.newroad.user.sns.service.message.sms;

import java.util.Random;

public class MessageConstant {

  public static final String MOBILE = "mobile";
  public static final String MESSAGE = "message";
  public static final String VERIFICATION_MESSAGE = "验证码：%s ，请勿向他人透露此验证码。";
  public static final String BACK_PASSWORD_MESSAGE = "验证码：%s ，请勿向他人透露此验证码。";
  public static String VERIFICATION_CODE_CACHE_KEY = "sns.verification.phone.";
  public static int VERIFICATION_CODE_CACHE_TIME = 60 * 10;// second unit

  public static final int MESSAGE_TYPE_NORMAL = 0;

  public static String generateRandomCode() {
    Random random = new Random();
    String result = "";
    for (int i = 0; i < 6; i++) {
      result += random.nextInt(10);
    }
    return result;
  }

  public static boolean isCodeTimeOut(long sendTime) {
    long currentTime = System.currentTimeMillis();
    Long timeLong = currentTime - sendTime;
    int timeCheck = timeLong.intValue() - VERIFICATION_CODE_CACHE_TIME;
    if (timeCheck > 0) {
      // Time out
      return false;
    }
    return true;
  }

  public enum ResponseCode {

    ok(0, "成功"), error(1, "失败"), r304(304, "客户端发送三次失败"), r305(305, "服务器返回了错误的数据，原因可能是通讯过程中有数据丢失"), r307(307, "发送短信目标号码不符合规则，手机号码必须是以0、1开头"), r308(
        308, "非数字错误，修改密码时如果新密码不是数字那么会报308错误"), r3(3, "连接过多，指单个节点要求同时建立的连接数过多"), r911005(911005, "客户端注册失败,序列号或者密码有误"), r911003(911003,
        "客户端注册失败,序列号已注册且与当前客户赋值的password不同"), r11(11, "企业信息注册失败"), r12(12, "查询余额失败"), r13(13, "充值失败"), r14(14, "手机转移失败"), r15(15,
        "手机扩展转移失败"), r16(16, "取消转移失败"), r17(17, "发送信息失败"), r18(18, "发送定时信息失败"), r22(22, "注销失败"), r27(27, "查询单条短信费用错误码");

    int code;
    String msg;

    ResponseCode(int code, String msg) {
      this.code = code;
      this.msg = msg;
    }

    public static ResponseCode fromCode(int code) {
      for (ResponseCode rcode : values()) {
        if (rcode.getCode() == code) {
          return rcode;
        }
      }
      return error;
    }

    public int getCode() {
      return code;
    }

    public String getMsg() {
      return msg;
    }
  }

}
