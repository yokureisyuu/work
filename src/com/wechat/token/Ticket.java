package com.wechat.token;

import org.apache.log4j.Logger;

public class Ticket extends Token
{
  private static Logger logger = Logger.getLogger(Ticket.class);
//  private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?";
//  private static final String TICKET_NAME = "ticket";
//  private static final String EXPIRESIN_NAME = "expires_in";
  private String type;

  public Ticket(TicketType ticketType)
  {
    this.type = ticketType.name();
  }

  protected String accessTokenUrl()
  {
    String access_token = TokenProxy.accessToken();
    String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=" + this.type;
    logger.info("获取ticket,ticket类型" + this.type);
    return url;
  }

  protected String tokenName()
  {
    return "ticket";
  }

  protected String expiresInName()
  {
    return "expires_in";
  }
}