package com;

/**
 * 本服务与微信的交互时，访问的微信接口
 * @author Administrator
 *
 */
public class URLUtil {

	public static String accessTokenUrl ="https://api.weixin.qq.com/cgi-bin/token";
	public static String creatMenu="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";
	public static String delMenu="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";
	public static String queryMenu="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";
	//发送客服消息
	public static String sendMessage="https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=";
}
