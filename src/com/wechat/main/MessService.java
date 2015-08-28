package com.wechat.main;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.Article;
import com.wechat.BasicEvent;
import com.wechat.CommonUtil;
import com.wechat.MenuEvent;
import com.wechat.MessageUtil;
import com.wechat.NewsMessage;
import com.wechat.QRCodeEvent;
import com.wechat.SubscribeEvent;
import com.wechat.TextMessage;
import com.wechat.VoiceMessage;
import com.wechat.sub.SilkTravelCheckWeixinTicketInfo;
import com.wechat.sub.SilkTravelGetWeixinUserInfo;
import com.wechat.sub.SilkTravelGroupUtil;
import com.wechat.sub.SilkTravelSendImageMessage;

public class MessService extends CommonUtil {

	protected final static Logger log = LoggerFactory
			.getLogger(MessService.class);

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 *            得到的xml请求
	 * @return
	 */
	public static String process(HttpServletRequest request) {
		String respMessage = "";
		try {
			// 默认返回的文本消息内容
			@SuppressWarnings("unused")
			String respContent = "";
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			
			BasicEvent basicEvent=new BasicEvent();
			basicEvent.setToUserName(toUserName);
			basicEvent.setFromUserName(fromUserName);
			basicEvent.setMsgType(msgType);
			String createTime= requestMap.get("CreateTime");
			basicEvent.setCreateTime(Long.parseLong(createTime));
			String event = requestMap.get("Event");
			basicEvent.setEvent(event);
			
			if (basicEvent.getMsgType().equals(RECRIVE_EVENT)) {
				// 接收消息类型：推送

				//   订阅
				if (basicEvent.getEvent().equals(EVENT_SUBSCRIBE)) {
					SubscribeEvent subscribeEvent=new SubscribeEvent( basicEvent);
					// 事件类型
					String eventKey = requestMap.get("EventKey");
					
					// 对话服务-接收消息-接收事件推送-扫描带参数二维码事件
					if (eventKey.equals("qrscene_2")) {
						
						// 景区管理员
						SilkTravelGroupUtil.removeMemberGroups(basicEvent.getFromUserName(),
								"景区管理员");
						
//						if (!SilkTravelGetWeixinUserInfo.CheckAdmin(basicEvent.getFromUserName())){
//						
//							SilkTravelSendCustomMessage.sendTextMessage(basicEvent.getFromUserName(), "你已成为景区管理员，能够检票！");
//							
//						}
						

					}
					respMessage=handleRecriveEvent(subscribeEvent);

				}else if (basicEvent.getEvent().equals(EVENT_UNSUBSCRIBE)) {
					// 取消订阅
					//  取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
				} else if (basicEvent.getEvent().equals(EVENT_QRCODE_SCAN)) {
					// 对话服务-接收消息-接收事件推送-扫描带参数二维码事件
					// Receive event push  
					basicEvent=new QRCodeEvent(basicEvent);
					
					if (basicEvent instanceof QRCodeEvent){
						// 事件类型：sacn(用户已关注时的扫描带参数二维码 )
						QRCodeEvent qRCodeEvent=(QRCodeEvent)basicEvent;
						// 事件类型
						String eventKey = requestMap.get("EventKey");
						qRCodeEvent.setEventKey(eventKey);
						String ticket = requestMap.get("Ticket");
						qRCodeEvent.setTicket(ticket);
						if (qRCodeEvent.getEventKey().equals("2")) {
							
							
							if (!SilkTravelGetWeixinUserInfo.CheckAdmin(qRCodeEvent.getFromUserName())){
								// 景区管理员
								SilkTravelGroupUtil.removeMemberGroups(qRCodeEvent.getFromUserName(),
										"景区管理员");
								TextMessage textMessage = new TextMessage();
								textMessage.setToUserName(basicEvent.getFromUserName());
								textMessage.setFromUserName(basicEvent.getToUserName());
								textMessage.setCreateTime(new Date().getTime());
								textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
								textMessage.setFuncFlag(0);
								textMessage.setContent("你已成为景区管理员，能够检票！");
								respMessage = MessageUtil.textMessageToXml(textMessage);
								
							}
							
						}
						
					}
				}else if (basicEvent.getEvent().equals(EVENT_CLICK)) {
					// 自定义菜单点击事件
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应

					MenuEvent menuEvent=new MenuEvent( basicEvent);
					String eventKey = requestMap.get("EventKey");
					menuEvent.setEventKey(eventKey);

					if (menuEvent.getEventKey().equals("bsbgyrtg")) {
						respContent = "巴仕拜羊羔肉推广菜单项被点击！";

						respMessage =handleRecriveEvent(basicEvent);
						

					} 
				}else if (basicEvent.getEvent().equals(EVENT_VIEW)) {
					// 自定义菜单点击事件
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					basicEvent=new MenuEvent(basicEvent);

					if (basicEvent instanceof MenuEvent){
						// 事件类型：sacn(用户已关注时的扫描带参数二维码 )
						MenuEvent menuEvent=(MenuEvent)basicEvent;
						String eventKey = requestMap.get("EventKey");
						menuEvent.setEventKey(eventKey);
						//if (menuEvent.getEventKey().equals("34")) {
							respContent = "购票菜单项被点击！";
							request.getSession().setAttribute("weixinId",basicEvent.getFromUserName());
							request.setAttribute("weixinId",basicEvent.getFromUserName());
						//}
					}

				}  else if (EVENT_TYPE_SCANCODE_WAITMSG
						.equals(basicEvent.getEvent())) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					QRCodeEvent qRCodeEvent=new QRCodeEvent(basicEvent);
					// 事件类型
					String eventKey = requestMap.get("EventKey");
					qRCodeEvent.setEventKey(eventKey);
					String ticket = requestMap.get("Ticket");
					qRCodeEvent.setTicket(ticket);
					
					 String scanType = requestMap.get("ScanType");
					qRCodeEvent.setScanType(scanType);
					String scanResult = requestMap.get("ScanResult");
					qRCodeEvent.setScanResult(scanResult);

					if (qRCodeEvent.getEventKey().equals("35")) {
						
						TextMessage textMessage = new TextMessage();
						textMessage.setToUserName(basicEvent.getFromUserName());
						textMessage.setFromUserName(basicEvent.getToUserName());
						textMessage.setCreateTime(new Date().getTime());
						textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
						textMessage.setFuncFlag(0);
						textMessage.setContent(scanResult);
						respMessage = MessageUtil.textMessageToXml(textMessage);
						
						
//						if (SilkTravelGetWeixinUserInfo.CheckAdmin(qRCodeEvent.getFromUserName())){
//							respMessage=handleRecriveEvent(qRCodeEvent);
//						}else{
//							
//							TextMessage textMessage = new TextMessage();
//							textMessage.setToUserName(basicEvent.getFromUserName());
//							textMessage.setFromUserName(basicEvent.getToUserName());
//							textMessage.setCreateTime(new Date().getTime());
//							textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
//							textMessage.setFuncFlag(0);
//							textMessage.setContent("你不是景区管理员，不能检票！");
//							respMessage = MessageUtil.textMessageToXml(textMessage);
//							
//						}
						
					}
				}
				
			}else if (basicEvent.getMsgType().equals(RECRIVE_TEXT)) {
				// 文本消息
				// 回复文本消息
				TextMessage textMessage = new TextMessage();
				textMessage.setToUserName(basicEvent.getFromUserName());
				textMessage.setFromUserName(basicEvent.getToUserName());
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setFuncFlag(0);
				String content = requestMap.get("Content");
				textMessage.setContent(content);

				respMessage=handleRecriveEvent(textMessage);

			} else if (basicEvent.getMsgType().equals(RECRIVE_IMAGE)) {
				// 图片消息
				respContent = "您发送的是图片消息！";
			}
			// 地理位置消息
			else if (basicEvent.getMsgType().equals(RECRIVE_LOCATION)) {
				respContent = "您发送的是地理位置消息！";
			}
			// 链接消息
			else if (basicEvent.getMsgType().equals(RECRIVE_LINK)) {
				respContent = "您发送的是链接消息！";
			}
			// 音频消息
			else if (basicEvent.getMsgType().equals(RECRIVE_VOICE)) {

				VoiceMessage voiceMessage = new VoiceMessage();
				voiceMessage.setToUserName(basicEvent.getFromUserName());
				voiceMessage.setFromUserName(basicEvent.getToUserName());
				voiceMessage.setCreateTime(new Date().getTime());
				voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				voiceMessage.setFuncFlag(0);
				String content =requestMap.get("Recognition");// 您说的是：
				voiceMessage.setContent(content);

				// 恺升电子公众号微信二维码
				respMessage=handleRecriveEvent(voiceMessage);
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
		return respMessage;
	}
	
	/**
	 * 关注
	 * @param subscribeEvent
	 * @return
	 */
	private static String handleRecriveEvent(SubscribeEvent subscribeEvent) {
		String respMessage = "谢谢您的关注！";

		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(subscribeEvent.getFromUserName());
		newsMessage.setFromUserName(subscribeEvent.getToUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(REQUEST_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("欢迎关注千秋建筑装饰");
		article.setDescription("千秋建筑装饰");
		article.setPicurl("http://m.yzyw.56jyt.com/yzywPath/upload/qrcode/weixin.jpg");
		article.setUrl("http://m.yzyw.56jyt.com/yzyw/indexhome_yzyw");
		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		// OrderService orderService;
		// orderService = (OrderService) MethodUtil.getBean(
		// ServletActionContext.getServletContext(),
		// "orderService");
		// String weixinID = textMessage.getToUserName();
		// orderService.updateOrderUserName(weixinID);

		return respMessage;
	}
	
	/**
	 * "扫一扫
	 * @param qRCodeEvent
	 * @return
	 */
	@SuppressWarnings("unused")
	private static String handleRecriveEvent(QRCodeEvent qRCodeEvent) {

		log.trace("扫一扫菜单项被点击！ fromUserName:{} scanResult:{}",
				qRCodeEvent.getFromUserName(), qRCodeEvent.getScanResult());
		 SilkTravelCheckWeixinTicketInfo
				.CheckWeixinTicketInfo(qRCodeEvent.getFromUserName(), qRCodeEvent.getScanResult());
		return "";
	}
	
	/**
	 * 文本消息
	 * @param textMessage
	 * @return
	 */
	private static String handleRecriveEvent(TextMessage textMessage) {

		// 您发送的是文本消息
		if (textMessage.getContent().contains("#glygzh") || textMessage.getContent().contains("管理员")) {
				// 恺升电子公众号微信二维码
				SilkTravelSendImageMessage.sendImageMessage(textMessage.getToUserName(),
						"admin.jpg");
				
		}else if (textMessage.getContent().contains("#gzh") || textMessage.getContent().contains("公众号")) {
			// 恺升电子公众号微信二维码
			SilkTravelSendImageMessage.sendImageMessage(textMessage.getToUserName(),
					"qrcode_for_silktravel.jpg");
			
		}
		
		return "";
		
	}
	
	/**
	 * 语音消息
	 * @param voiceMessage
	 * @return
	 */
	private static String handleRecriveEvent(VoiceMessage voiceMessage) {
		String respContent="";
		// 您发送的是文本消息
		if ( voiceMessage.getContent().contains("管理员")) {
			// 恺升电子公众号微信二维码
			SilkTravelSendImageMessage.sendImageMessage(voiceMessage.getToUserName(),
					"admin.jpg");
			
		}else if ( voiceMessage.getContent().contains("公众号")) {
			// 恺升电子公众号微信二维码
			SilkTravelSendImageMessage.sendImageMessage(voiceMessage.getToUserName(),
					"qrcode_for_silktravel.jpg");
			
		}else{
			String format = "您说的是：%s！";
			respContent = String.format(format, voiceMessage.getContent());
			TextMessage  textMessage=voiceMessage;
			textMessage.setContent(respContent);
			respContent = MessageUtil.textMessageToXml(textMessage);
		}
		return respContent;
		
	}
	
	
	/**
	 * 巴仕拜羊羔肉推广
	 * @param subscribeEvent
	 * @return
	 */
	private static String handleRecriveEvent(BasicEvent subscribeEvent) {
		String respMessage = "谢谢您的关注！";

		// 创建图文消息
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(subscribeEvent.getFromUserName());
		newsMessage.setFromUserName(subscribeEvent.getToUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(REQUEST_NEWS);
		newsMessage.setFuncFlag(0);

		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("千秋建筑装饰");
		article.setDescription("“千秋建筑装饰”地标性农产品O2O电子商务平台众筹说明及报名表");
		article.setPicurl("http://m.yzyw.56jyt.com/yzywPath/upload/pic/yzyw.jpg");
		article.setUrl("http://m.yzyw.56jyt.com/yzyw/announcementgetDetail.action?id=1");
		articleList.add(article);
		// 设置图文消息个数
		newsMessage.setArticleCount(articleList.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articleList);
		// 将图文消息对象转换成xml字符串
		respMessage = MessageUtil.newsMessageToXml(newsMessage);

		// OrderService orderService;
		// orderService = (OrderService) MethodUtil.getBean(
		// ServletActionContext.getServletContext(),
		// "orderService");
		// String weixinID = textMessage.getToUserName();
		// orderService.updateOrderUserName(weixinID);

		return respMessage;
	}
	
}