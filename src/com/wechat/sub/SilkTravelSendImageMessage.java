package com.wechat.sub;

import order.web.util.Constant;

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
public class SilkTravelSendImageMessage extends CommonUtil {
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static boolean sendImageMessage(String openId, String qrCodeName) {
		log.info("消息内容：{}", qrCodeName);
		
		// 获取接口访问凭证
		String accessToken = getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET)
				.getAccesstoken();

		try {

			String filePath = Constant.qrCodeUrl + "/upload/map/qrcode/" +qrCodeName;

			// 上传多媒体文件
			Media weixinMedia = MediaUtil.uploadMedia(accessToken, "image",
					filePath);
			String mediaId = weixinMedia.getMediaId();
			System.err.println(weixinMedia.getMediaId());
			System.err.println(weixinMedia.getType());
			System.err.println(weixinMedia.getCreatedAt());

			// 组装文本客服消息
			String jsonTextMsg = MakeCustomMessage.makeImageCustomMessage(
					openId, mediaId);
			// 发送客服消息
			boolean result=SendCustomMessage.sendCustomMessage(accessToken, jsonTextMsg);

			return result;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}