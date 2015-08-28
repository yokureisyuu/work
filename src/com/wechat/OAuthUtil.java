package com.wechat;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * OAuth2授权工具类
 * 
 * @author tiangai
 * @since 2014-06-30 Am 10:47
 * @version 1.0
 */
public class OAuthUtil extends CommonUtil {
	/**
	 * 通过code换取网页授权access_token
	 * 
	 * @param appId
	 *            公众号的唯一标识
	 * @param appSecret
	 *            公众号的appsecret
	 * @param code
	 *            填写第一步获取的code参数
	 * @return WeixinOauth2Token
	 */
	public static Oauth2Token getOauth2AccessToken(String appId,
			String appSecret, String code) {
		Oauth2Token wat = null;
		String requestUrl = OAUTH2_ACCESSTOKEN_URL.replace("APPID", appId)
				.replace("SECRET", appSecret).replace("CODE", code);
		// 获取网页授权凭证
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new Oauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				System.err.println(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 刷新access_token
	 * 
	 * @param appId
	 *            公众号的唯一标识
	 * @param refreshToken
	 *            通过access_token获取到的refresh_token参数
	 * @return
	 */
	public static Oauth2Token refreshOauth2Token(String appId,
			String refreshToken) {
		Oauth2Token wat = null;
		String requestUrl = REFRESH_ACCESSTOKEN_URL.replace("APPID", appId)
				.replace("REFRESH_TOKEN", refreshToken);
		// 获取网页授权凭证
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				wat = new Oauth2Token();
				wat.setAccessToken(jsonObject.getString("access_token"));
				wat.setExpiresIn(jsonObject.getInt("expires_in"));
				wat.setRefreshToken(jsonObject.getString("refresh_token"));
				wat.setOpenId(jsonObject.getString("openid"));
				wat.setScope(jsonObject.getString("scope"));
			} catch (Exception e) {
				wat = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("刷新网页授权凭证失败 errcode:{} errmsg:{}", errorCode,
						errorMsg);
			}
		}
		return wat;
	}

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param accessToken
	 *            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * @param openId
	 *            用户的唯一标识
	 * @return SNSUserInfo
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
		SNSUserInfo sui = null;
		String requestUrl = OAUTH2_USERINFO_URL.replace("ACCESS_TOKEN",
				accessToken).replace("OPENID", openId);
		// 通过网页授权获取用户信息
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
		if (null != jsonObject) {
			try {
				sui = new SNSUserInfo();
				// 用户标识
				sui.setOpenId(jsonObject.getString("openid"));
				// 昵称
				sui.setNickname(jsonObject.getString("nickname"));
				// 性别(1是男性，2是女鞋，3是未知)
				sui.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				sui.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				sui.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				sui.setCity(jsonObject.getString("city"));
				// 用户头像
				sui.setHeadImgUrl(jsonObject.getString("headimgurl"));
				// 用户特权信息
				sui.setPrivilegeList(JSONArray.toList(
						jsonObject.getJSONArray("privilege"), List.class));
			} catch (Exception e) {
				sui = null;
				int errorCode = jsonObject.getInt("errcode");
				String errorMsg = jsonObject.getString("errmsg");
				log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
			}
		}
		return sui;
	}

	public static void main(String[] args) {
		Oauth2Token weixinOauth2Token = getOauth2AccessToken(
				WEIXIN_APPID, WEIXIN_APPSECRET,
				"010b4e2569123307d29f8c65daa592df");
		System.err.println("weixintoken是：" + weixinOauth2Token);
		String accessToken = weixinOauth2Token.getAccessToken();
		System.err.println("accestoken是" + accessToken);
		String openId = weixinOauth2Token.getOpenId();
		System.err.println("openId是" + openId);
		SNSUserInfo snsUserInfo = getSNSUserInfo(accessToken, openId);
		System.err.println("snsUserInfo是" + snsUserInfo);

	}
}
