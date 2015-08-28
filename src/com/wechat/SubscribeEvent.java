package com.wechat;

/**  
*   
* 项目名称：wechatapi  
* 类名称：SubscribeEvent  
* 类描述：订阅/取消订阅 事件  
* 创建人：Myna Wang  
* 创建时间：2014-3-11 上午11:36:25  
* @version       
*/
public class SubscribeEvent extends BasicEvent{
	public SubscribeEvent(BasicEvent basicEvent){  
        setToUserName(basicEvent.getToUserName());
        setFromUserName(basicEvent.getFromUserName());
        setCreateTime(basicEvent.getCreateTime());
        setMsgType(basicEvent.getMsgType());
        setEvent(basicEvent.getEvent());
    }  

}
