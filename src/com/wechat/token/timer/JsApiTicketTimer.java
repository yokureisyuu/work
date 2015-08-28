package com.wechat.token.timer;

import java.util.TimerTask;
import org.apache.log4j.Logger;


import com.wechat.token.Ticket;
import com.wechat.token.TicketType;
import com.wechat.token.server.CustomerServer;
import com.wechat.token.server.JsApiTicketServer;


public class JsApiTicketTimer extends TimerTask
{
  private static Logger logger = Logger.getLogger(JsApiTicketTimer.class);
  public static final long PERIOD = 7000000L;
  public static final long DELAY = 0L;

  public void run()
  {
    logger.info("jsapi_ticket 定时任务启动，获取新的jsapi_ticket");

    Ticket jsapiTicket = new Ticket(TicketType.jsapi);

    if (jsapiTicket.request()) {
      JsApiTicketServer jsapiTicketServer = new JsApiTicketServer();
      CustomerServer customerServer = (CustomerServer)jsapiTicketServer
        .customerServer();
      customerServer.save(jsapiTicket);
    }
  }
}