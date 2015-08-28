package com.wechat;

import net.sf.json.JSONObject;

/**  
*   
* 项目名称：wechatapi  
* 类名称：SendCustomMessage  
* 类描述：发送客服消息工具 
* 创建人：Myna Wang  
* 创建时间：2014-3-7 上午10:37:08  
* @version       
*/
public class SendCustomMessage extends CommonUtil{
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken 接口访问凭证
	 * @param jsonMsg json格式客服消息
	 * @return true|false
	 */
	public static boolean sendCustomMessage(String accessToken,String jsonMsg) {
		log.info("消息内容：{}",jsonMsg);
		boolean result=false;
		String requestUrl=SEND_CUSTOM_URL.replace("ACCESS_TOKEN", accessToken);
		// 发送客服消息
		JSONObject jsonObject=httpRequest(requestUrl, "POST", jsonMsg);
		if (null!=jsonObject) {
			int errorCode=jsonObject.getInt("errcode");
			String errorMsg=jsonObject.getString("errmsg");
			if (0 == errorCode) {
				result=true;
				log.info("发送客服消息成功 errCode:{} errormsg:{} ",errorCode,errorMsg);
			}else {
				log.error("发送客服消息失败 errCode:{} errormsg:{} ",errorCode,errorMsg);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {

		// 获取接口访问凭证
				String accessToken = getAccessToken(WEIXIN_APPID,
						WEIXIN_APPSECRET).getAccesstoken();

		// 组装文本客服消息
		String jsonTextMsg=MakeCustomMessage.makeTextCustomMessage("odIK5uJFzt2cg1zZTEpTVdx8sJVo", "哈哈哈哈");
		// 发送客服消息
		sendCustomMessage(accessToken, jsonTextMsg);
	}
}
