package com.wechat;

/**
 * 文本消息 
 * xidian.wq.autoreply.message.request
 * @author  WQ
 * 2013-8-8 下午9:31:22
 */
public class TextMessage extends RespBaseMessage{
	 // 消息内容  
    private String Content;  
  
    public String getContent() {  
        return Content;  
    }  
  
    public void setContent(String content) {  
        Content = content;  
    }  
} 
