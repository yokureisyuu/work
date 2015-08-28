package com.wechat.sub;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import order.web.util.Constant;
import order.web.util.QRCodeUtil;

import com.wechat.Article;
import com.wechat.CommonUtil;
import com.wechat.MakeCustomMessage;
import com.wechat.Media;
import com.wechat.MediaUtil;
import com.wechat.SendCustomMessage;

/**
 * 
 * 项目名称：wechatapi 类名称：SendCustomMessage 类描述：发送客服消息工具 创建人：Myna Wang 创建时间：2014-3-7
 * 上午10:37:08
 * 
 * @version
 */
public class SilkTravelSendCustomMessage extends CommonUtil {
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static boolean sendCustomMessage(String openId, String checkcode) {
		log.info("消息内容：{}", checkcode);

		// 获取接口访问凭证
		String accessToken = getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET)
				.getAccesstoken();

//		String picname = "E:/work/project/workspace/silktravel/web/images/lopvillageLogo.jpg";
//		String logoPath;
		try {
//			logoPath = Constant.path + "pic" + "/"
//					+ MethodUtil.getFileName(picname);

			String qrCodePath = Constant.path + "qrcode";
			String qrCodeName = new Date().getTime()+ ".png" ;

			QRCodeUtil
					.encode(checkcode, "", qrCodePath, true, qrCodeName);
			
			SilkTravelQrcodeTicket ticket=new SilkTravelQrcodeTicket();
			ticket.ticket(qrCodePath+"/"+qrCodeName,qrCodePath+"/"+checkcode+ ".jpg");

			String filePath = Constant.qrCodeUrl + "/upload/qrcode/" +checkcode+ ".jpg";

			// 上传多媒体文件
			Media weixinMedia = MediaUtil.uploadMedia(accessToken, "image",
					filePath);
//			String mediaId = weixinMedia.getMediaId();
			System.err.println(weixinMedia.getMediaId());
			System.err.println(weixinMedia.getType());
			System.err.println(weixinMedia.getCreatedAt());

			// 组装文本客服消息
//			String jsonTextMsg = MakeCustomMessage.makeImageCustomMessage(openId, mediaId);
			
			// 创建图文消息
			String content="亲爱的用户，您的订单已经提交成功，请保存好门票。";
			List<Article> articleList = new ArrayList<Article>();
			Article article = new Article();
			article.setTitle(content);
			article.setDescription("罗布人村寨二维码门票");
			article.setPicurl(filePath);
			article.setUrl("http://lbrcz.56jyt.com/lopvillage/index_tt");
			articleList.add(article);
			String jsonTextMsg = MakeCustomMessage.makeNewsCustomMessage(openId, articleList);
			log.info("消息内容jsonTextMsg：{}", jsonTextMsg);
			// 发送客服消息
			boolean result=SendCustomMessage.sendCustomMessage(accessToken, jsonTextMsg);

			return result;
			

		} catch (Exception e) {
			e.printStackTrace();
			 log.error("https请求异常: "+e);
			return false;
		}
	}
	
	
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static boolean sendTextMessage(String openId, String content) {
		log.info("消息内容：{}", content);

		// 获取接口访问凭证
		String accessToken = getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET)
				.getAccesstoken();

	
		try {
			// 组装文本客服消息
			String jsonTextMsg = MakeCustomMessage.makeTextCustomMessage(
					openId, content);
			// 发送客服消息
			boolean result=SendCustomMessage.sendCustomMessage(accessToken, jsonTextMsg);

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}