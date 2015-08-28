package com.wechat;


/**
 * 微信公众账号唯一凭证
 * 
 * @author tiangai
 * @since 2014-06-30 Am 9:35
 * @version 1.0    
*/
public class AccessToken{
	/**
 	 * 获取到的凭证
 	 */
	private String accesstoken;
	/**
	 * 凭证有效时间，单位：秒  有效期为7200秒
	 */
	private int expiresin;
	public String getAccesstoken() {
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}
	public int getExpiresin() {
		return expiresin;
	}
	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}
}
