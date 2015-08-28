package com.wechat.token.server;

import com.wechat.common.Config;



public class JsApiTicketServer extends AbsServer
  implements TicketServer
{
  public String ticket()
  {
    return super.token();
  }

  protected String getCustomerServerClass()
  {
    return Config.instance().getJsApiTicketServer();
  }

  public IServer defaultServer()
  {
    return JsApiTicketMemServer.instance();
  }
}