package com.newroad.user.sns.service.message.sms;




/**
 * Emay sms response model
 * 
 * @author: xiangping_yu
 * @data : 2014-4-4
 * @since : 1.5
 */
public class EmaySmsResponseModel {

  private int code;
  private String msg;

  public boolean success() {
    return MessageConstant.ResponseCode.ok.getCode()==code;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  /**
   * Emay receive sms response model
   */
  public static class EmaySmsReceiveResponseModel extends EmaySmsResponseModel {

  }
}
