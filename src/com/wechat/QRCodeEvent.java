package com.wechat;

/**  
*   
* 项目名称：wechatapi  
* 类名称：QRCodeEvent  
* 类描述：二维码扫描事件  
* 创建人：Myna Wang  
* 创建时间：2014-3-11 上午11:37:18  
* @version       
*/
public class QRCodeEvent extends BasicEvent{
	// 事件key值
	private String EventKey; 
	// 二维码的ticket，可用来换取二维码图片 
	private String Ticket;
	
	// 扫描类型 
	private String  scanType;
	
	// 扫描结果 
	private String  scanResult;
	
	public QRCodeEvent(BasicEvent basicEvent){  
        setToUserName(basicEvent.getToUserName());
        setFromUserName(basicEvent.getFromUserName());
        setCreateTime(basicEvent.getCreateTime());
        setMsgType(basicEvent.getMsgType());
        setEvent(basicEvent.getEvent());
    }  

	public String getEventKey() {
		return EventKey;
	}
	
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	
	public String getTicket() {
		return Ticket;
	}
	
	public void setTicket(String ticket) {
		Ticket = ticket;
	}

	public String getScanType() {
		return scanType;
	}

	public void setScanType(String scanType) {
		this.scanType = scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	public void setScanResult(String scanResult) {
		this.scanResult = scanResult;
	}
}
