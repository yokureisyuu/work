package order.web.util;

/**
 * 定义与微信交互时的字符串常量
 * @author Administrator
 *
 */
public class ConstantUtil {


	
	public static String ENCODE="UTF-8";

	//认证时的常量
	public static String SIGNATURE="signature";
	public static String TIMESTAMP="timestamp";
	public static String NONCE="nonce";
	public static String ECHOSTR="echostr";
	
	//获取Access Token时
	public static String GRANT_TYPE="grant_type";
	public static String APPID="appid";
	public static String SECRET="secret";
	
	//接收消息时用到
	public static String TOUSERNAME = "ToUserName";
	public static String FROMEUSERNAME = "FromUserName";
	public static String CREATTIME = "CreateTime";
	public static String MSGTYPE = "MsgType";
	public static String CONTENT = "Content";
	public static String EVENT = "event";
	public static String MEDIAID = "MediaId";
	public static String FORMAT = "Format";
	public static String RECOGNITION = "Recognition";
	public static String MSGID = "MsgId";
	
	//接受消息时的消息类型
	public static String MSG_TEXT="text";
	public static String MSG_IMAGE="image";
	public static String MSG_VOICE="voice";
	public static String MSG_VIDEO="video";
	public static String MSG_LOCATION="location";
	public static String MSG_LINK="link";
	public static String MSG_EVENT="event";
	public static String MSG_ENVE_kEY="EventKey";
	
	//创建菜单时
	public static String MENU_CLICK="click";
	public static String MENU_VIEW="view";
	public static String MENU_BUTTON="button";
	public static String MENU_TYPE="type";
	public static String MENU_NAME="name";
	public static String MENU_KEY="key";
	public static String MENU_URL="url";
	public static String MENU_SUB_BUTTON="sub_button";
	
	
	public static String ACCESSTOKEN = "access_token";
	public static String EXPIRES_IN = "expires_in";
	
	//访问微信接口时返回json时
	public static String ERRCODE = "errcode";
	public static String ERRMSG = "errmsg";
	
//	public static String TOUSER="touser";
//	public static String MSGTYPE="msgtype";
//	public static String TEXT="text";
	//public static String CONTENT="content";
	
}
