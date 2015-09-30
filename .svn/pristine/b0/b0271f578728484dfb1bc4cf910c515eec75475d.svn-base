package com.newroad.user.sns.event;

import com.newroad.user.sns.model.login.LoginContext;

/**
 * @info : 登录后触发的事件
 * @author: xiangping_yu
 * @data : 2013-11-6
 * @since : 1.5
 */
public class AfterLoginEvent implements Event<Object> {

  private static final String CONNECTOR = "getCosConnector";

  private String host = "http://file.lenovomm.com/supernotefile/v1/";

  @Override
  public Object doEvent(Object... q) {
    LoginContext context = (LoginContext) q[0];
//    try {
//
//      JSONObject param = new JSONObject();
//      param.put("lenovoST", context.getLpsust());
//      param.put("realm", context.getRealm());
//      String result = HttpUtil.httpCall(Method.post, host + CONNECTOR, null, new StringBuffer(param.toString()), null).toString();
//
//      log.debug("AfterLoginEvent.doEvent>>>>>>>>>>>>:" + result);
//      if (StringUtils.isBlank(result)) {
//        throw new RuntimeException("Get cos connector error. Because the result is blank.");
//      }
//
//      JSONObject json = JSONObject.fromObject(result);
//      String connector = (String) json.get("cosConnector");
//      if (StringUtils.isBlank(connector)) {
//        log.error("get cos connector error!");
//      }
//      context.setCosConnector(connector);
//    } catch (Exception e) {
//      log.error("get cos connector error:", e);
//    }
    return context;
  }

  public void setHost(String host) {
    this.host = host;
  }
}
