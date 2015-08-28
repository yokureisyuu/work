package com;

/**
 * token为自定义
 * appid,secret,为从微信中获取得到
 * accessToken,expirein 为从微信中通过appid,secret它获取得到
 * 
 * @author Administrator
 *
 */
public class SecretUtil {

	//成为开发者平台的URL TOKE如下
	//URL:http://test.56jyt.com/jyt/msg.do
	
	//罗兰居toke:如下
	
    //吉运通token
	//public static String token="12312312312q3123aweqweqeqeqweqwe";
	//吉运通订阅号token
	//public static String token="34qerqwqeqweqasdasdaqwe1234";
	//appid,appSecret值在成为开发者后，从微信公众账号里面查询获取
	//罗兰居服务号
	//public static String token="131313asdfasrdasdasdasdaea";
	//public static String appId="wx4c1edae8530f3534";
	//public static String appSecret="391cc5f1976f1515f4bf6b0b5552626f";
	
	//恺升电子订阅号

	public static String token="131313asdfasrdasdasdasdaea";
	public static String appId="wx3dda302fe708aee6";
	public static String appSecret="9614e61d55440dc39e87fb46ca12c294";
	
	//裕民县旅游局服务号
//	public static String token="test1sofshlsfsgnbmsopiljie";
//	public static String appId="wx8c4f737c60713357";
//	public static String appSecret="5e9bf2352a405a001e268bce6df37e38";
	
	//吉运通服务号
	//public static String token="1231312313123123as";
	//public static String appId="wx4da35403b6a44393";
	//public static String appSecret="bd23790647d87a6e0f59842371b56641";
	
	//阿罗服务号
	//public static String token="sdfssasafasfsasdsd";
	//public static String appId="wx307fcdc2d9b144e4";
	//public static String appSecret="c2abdbe5791b3bdbb747701332181850";
	
	//吉运订阅号
	//public static String token="1231231231232132";
	//public static String appId="wx3f2532d9ae1d88f1";
	//public static String appSecret="2d32c0e251281f4f882b2fba3999b2bc";
	
	public static String grantType="client_credential";
	
	//用于公共空间的，每次Tomcat启动只能取值一次,因为每人次数有限
	public static String accessToken="6qZShXLOr1E9CRol5BVGyotzHgIHxh1M16qw6RmKj8R6fG9bmrskbWXOtYh_LuxA4gOMC6AOdTG86yAtmrhA7Q";
	//accessToken的有效期,单位为秒，微信公共账户默认为7200秒,即2小时
	public static Integer expirein=7200;
	
	public static boolean accessTokenStatus=false;
	
	
}
