package com.wechat.sub;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.wechat.Article;
import com.wechat.CommonUtil;
import com.wechat.MakeCustomMessage;

/**
 * 
 * 项目名称：wechatapi 类名称：SendCustomMessage 类描述：发送客服消息工具 创建人：Myna Wang 创建时间：2014-3-7
 * 上午10:37:08
 * 
 * @version
 */
public class SilkTravelCheckWeixinTicketInfo extends CommonUtil {
	/**
	 * 验证门票方法
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static String CheckWeixinTicketInfo(String opeinid, String ticket) {

		log.trace("验证门票：opeinid:{} ticket:{}", opeinid, ticket);

		String result = "";
		String ticketCheckUrl = "http://silktravel.56jyt.com/silktravel/interOrdercheck?checkcode=CHECKCODE";
		String requestUrl = ticketCheckUrl.replace("CHECKCODE", ticket);
		// 获取网页授权凭证
		JSONObject jsonObject = http(requestUrl, "GET", null);

		if (null != jsonObject) {
			try {

				result = jsonObject.getString("code");

				if (result.equals("0000")) {
					result = "0000";
					SilkTravelSendCustomMessage.sendTextMessage(opeinid,
							"二维码电子门票:" + ticket + "验证成功！");

//					OrderService orderService = new OrderService();
//					orderService = (OrderService) MethodUtil.getBean(
//							ServletActionContext.getServletContext(),
//							"orderService");
//
//					OrderSummaryDDO order = orderService
//							.getOrderByCheckCode(ticket);
//					if (StringUtils.isNotBlank(order.getWeixinId())) {
//
//						String format = "二维码电子门票:TICKET DATE 已消费完毕！";
//						String message = format.replace("TICKET", ticket)
//								.replace("DATE",
//										MethodUtil.formatDateTime(new Date()));
//
//						SilkTravelSendCustomMessage.sendTextMessage(
//								order.getWeixinId(), message);
//					}

				} else {
					result = jsonObject.getString("content");
					SilkTravelSendCustomMessage.sendTextMessage(opeinid,
							"二维码电子门票:" + ticket + result + "！");
				}

			} catch (Exception e) {

				log.error("验证门票失败", e);
			}
		}
		return result;
	}

	

	public static void main(String[] args) {

		// 发送客服消息(测试本接口的前提是需要你先跟公众账号会话且在48小时内)

		// 创建图文消息
		String content = "亲爱的用户，您的订单已经提交成功，请保存好门票。";
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle(content);
		article.setDescription("罗布人村寨二维码门票");
		article.setPicurl("filePath");
		article.setUrl("http://lbrcz.56jyt.com/lopvillage/index_tt");
		articleList.add(article);
		 MakeCustomMessage.makeNewsCustomMessage("openId",
				articleList);

	}

}