package com.wechat.token.server;

import com.wechat.token.Token;

public abstract class CustomerServer
  implements IServer
{
  public String token()
  {
    return find();
  }

  public abstract boolean save(Token paramToken);

  protected abstract String find();
}