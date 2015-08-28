package com.wechat;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
*
* 项目名称：wechatapi
* 类名称：GetPersoninf
* 类描述：获取用户基本个人信息方法
* 创建人：Myna Wang
* 创建时间：2014-3-7 下午1:43:39
* @version
*/
public class GetPersoninf extends CommonUtil{
	/**
	 * 获取用户基本个人信息
	 *
	 * @param accessToken 调用接口凭证
	 * @param openid 普通用户的标识，对当前公众号唯一
	 * @return PersonalInf 基本个人信息
	 */
	public static PersonalInf getPersonalInf(String accessToken,String openId){
		PersonalInf personalInf=null;
		String requestUrl = GET_PERSONALINF_URL.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
    	// 获取用户信息
		JSONObject jsonObject = httpRequest(requestUrl, "GET", null);
	    // 如果请求成功
	    if (null != jsonObject) {
	        try {
	        	personalInf = new PersonalInf();
	        	// 关注状态(1为关注，0为未关注)，未关注时获取不到其余信息
	        	personalInf.setSubscribe(jsonObject.getInt("subscribe"));
	        	// 用户的标识
	        	personalInf.setOpenid(jsonObject.getString("openid"));
	        	// 昵称
	        	personalInf.setNickname(jsonObject.getString("nickname"));
	        	// 用户性别
	        	personalInf.setSex(jsonObject.getInt("sex"));
	        	// 用户的语言，简体中文为zh_CN
	        	personalInf.setLanguage(jsonObject.getString("language"));
	        	// 用户城市
	        	personalInf.setCity(jsonObject.getString("city"));
	        	// 用户省份
	        	personalInf.setProvince(jsonObject.getString("province"));
	        	// 用户国家
	        	personalInf.setCountry(jsonObject.getString("country"));
	        	// 用户头像
	        	personalInf.setHeadimgurl(jsonObject.getString("headimgurl"));
	        	// 关注时间
	        	personalInf.setSubscribetime(jsonObject.getString("subscribetime"));
	        } catch (Exception e) {
	        	// 如果openid没有，说明是假的openid
	        	if (null==personalInf.getOpenid()||"".equals(personalInf.getOpenid())) {
	        		log.error("用户:{} 不存在",openId);
				}else {
					if (0 == personalInf.getSubscribe()) {
						log.error("用户:{} 已取消关注",personalInf.getOpenid());
					}else if (1 == personalInf.getSubscribe()) {
						log.error("用户:{}已关注",personalInf.getOpenid());
					}else {
						int errorCode=jsonObject.getInt("errcode");
						String errorMsg=jsonObject.getString("errmsg");
			            // 获取token失败
			            log.error("获取基本个人信息失败 errcode:{} errmsg:{} ", errorCode, errorMsg);
					}
				}
	        }
	    }
		return personalInf;
	}

	/**
	 * 查询用户所在分组
	 *
	 * @param accessToken 调用接口凭证
	 * @param openId 普通用户的标识，对当前公众号唯一
	 * @return groupid
	 */
	public static int getPersonGroupId(String accessToken,String openId) {
		int groupId=0;
		String requestUrl=GET_PERSONGROUPID_URL.replace("ACCESS_TOKEN", accessToken);
		// 需要提交的json数据
		String jsonData="{\"openid\":\"%s\"}";
		// 创建分组
		JSONObject jsonObject=httpRequest(requestUrl, "POST",
				String.format(jsonData, openId));
		if (null!=jsonObject) {
			try {
				groupId=jsonObject.getInt("groupid");
			} catch (JSONException e) {
				groupId=-1;
				int errorCode=jsonObject.getInt("errcode");
				String errorMsg=jsonObject.getString("errmsg");
				log.error("查询用户所在分组失败 errcode:{} errmsg:{} ", errorCode, errorMsg);
			}
		}
		return groupId;
	}

	public static void main(String[] args) {
		// 获取接口访问凭证
		String accessToken=getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET).getAccesstoken();
		// 获取用户基本信息
		PersonalInf personalInf=getPersonalInf(accessToken, "openId");
		System.out.println(personalInf.getOpenid());

		// 查询用户所在分组
		int groupid=getPersonGroupId(accessToken, "openId");
		System.err.println("组id是："+groupid);
	}
}
