package com.wechat.sub;

import java.util.List;

import net.sf.json.JSONException;

import com.wechat.CommonUtil;
import com.wechat.GetPersoninf;
import com.wechat.GroupUtil;
import com.wechat.OAuthUtil;
import com.wechat.Oauth2Token;
import com.wechat.SNSUserInfo;
import com.wechat.WeixinGroup;

/**
 * 
 * 项目名称：wechatapi 类名称：SendCustomMessage 类描述：发送客服消息工具 创建人：Myna Wang 创建时间：2014-3-7
 * 上午10:37:08
 * 
 * @version
 */
public class SilkTravelGetWeixinUserInfo extends CommonUtil {
	/**
	 * 发送客服消息方法
	 * 
	 * @param accessToken
	 *            接口访问凭证
	 * @param jsonMsg
	 *            json格式客服消息
	 * @return true|false
	 */
	public static SNSUserInfo getUserInfo(String code) {
		log.info("消息内容：{}", code);
		try {

			Oauth2Token weixinOauth2Token = OAuthUtil.getOauth2AccessToken(
					WEIXIN_APPID, WEIXIN_APPSECRET, code);
			System.err.println("weixintoken是：" + weixinOauth2Token);
			String accessToken = weixinOauth2Token.getAccessToken();
			System.err.println("accestoken是" + accessToken);
			String openId = weixinOauth2Token.getOpenId();
			System.err.println("openId是" + openId);
			SNSUserInfo snsUserInfo = OAuthUtil.getSNSUserInfo(accessToken,
					openId);
			System.err.println("snsUserInfo是" + snsUserInfo);

			return snsUserInfo;

		} catch (Exception e) {
			e.printStackTrace();
			log.error("https请求异常: {}", e);
			return null;
		}
	}

	/**
	 * 查询用户所在分组
	 *
	 * @param accessToken
	 *            调用接口凭证
	 * @param openId
	 *            普通用户的标识，对当前公众号唯一
	 * @return groupid
	 */
	public static int getPersonGroupId(String openId) {
		int groupId = 0;
		try {
			// 获取接口访问凭证
			String accessToken = getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET)
					.getAccesstoken();

			// 查询用户所在分组
			groupId = GetPersoninf.getPersonGroupId(accessToken, openId);

			System.err.println("组id是：" + groupId);

		} catch (JSONException e) {
			groupId = -1;

			log.error("查询用户所在分组失败  ");
		}

		return groupId;
	}
	
	/**
	 * 管理员权限检查
	 * 
	 * @param opeinid
	 *            微信ID
	 * @return true|false
	 */
	public static Boolean CheckAdmin(String opeinid) {

		log.trace("管理员权限检查：opeinid:{} ", opeinid);
		Boolean result = false;
		int groupId = getPersonGroupId(opeinid);

		// 获取接口访问凭证
		String accessToken = getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET)
				.getAccesstoken();

		// 获取分组列表
		List<WeixinGroup> groupList = GroupUtil.getGroups(accessToken);
		// 循环输出各分组信息
		for (WeixinGroup group : groupList) {

			if (group.getName().equals("景区管理员") && group.getId() == groupId) {
				result = true;
				break;
			}
		}

		return result;
	}

}