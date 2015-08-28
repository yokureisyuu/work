package order.web.util;

public class Constant {

	//上传图片类型
    public static String IMAGE_TYPE_GIF=".gif";
    public static String IMAGE_TYPE_JPG=".jpg";
    public static String IMAGE_TYPE_JPEG=".jpeg";
    public static String IMAGE_TYPE_PNG=".png";
    //上传文件错误
    public static String CHECK_FILE_TYPE="check_file_type";
	//通用错误
	public static String COMMON_ERROR="common_error";
	//非法操作
	public static String USER_ILLEGAL_OP="user_illegal_op";
	//用户登录错误
	public static String USER_LOGIN_ERROR="user_login_error";
	
	//修改密码错误
	public static String USER_MODIFY_PWD_ERROR="user_modify_pwd_error";
	//修改手机号码错误
	public static String USER_MODIFY_PHONE_ERROR="user_modify_phone_error";
	
	//修改管理员名称或手机错误
	public static String USER_NAME_EXIT_ERROR="user_name_exit_error";
	public static String USER_PHONE_EXIT_ERROR="user_phone_exit_error";
	
	//增加或修改房间重复
	public static String SCENERY_ADD_REPEAT_ERROR= "scenery_add_repeat_error";
	
	//增加或修改餐饮重复
	public static String FOOD_ADD_REPEAT_ERROR= "food_add_repeat_error";
	
	//登录商店错误
	public static String STORE_LOGIN_ERROR= "store_login_error";
	
	//修改商店名称重复
	public static String STORE_MODIFY_NAME_ERROR="store_modify_name_error";
	
	//增加修改房间名称错误
	public static String SCENERY_NAME_ERROR="scenery_name_error";
	
	//增加修改风景价格错误
	public static String SCENERY_PRICE_ERROR="scenery_price_error";
	//打折错误
	public static String SCENERY_DISCOUNT_ERROR="scenery_discount_error";
	
	//订单状态
	//0，未有订单，1，未完成，2已完成
	public static int ORDER_STATUS_INIT=1;
	public static int ORDER_STATUS_FINISH=2;
	public static int ORDER_STATUS_CONFIRM=3;

	//订单类型，1订房间，2订餐饮
	public static int ORDER_TYPE_ROOM=1;
	public static int ORDER_TYPE_MEAL=2;
	//此变量保存菜单图片的位置，从web.xml中获得，必须与yzyw项目里图片位置一致
	public static String path;
	//二维码扫描url
	public static String qrCodeUrl;
	
	public static String doMain;
	
	public static String SUCCESS="success";
	
	//页面跳转
	public static String PAGE_LOGIN="page_login";
	public static String PAGE_INDEX="page_index";
	
	//跳转到设置到达指引页面
	public static String PAGE_WAY="page_way";
	//跳转到设置到达指引页面
	public static String PAGE_DISCOUNT="page_discount";
	
	public static String DIAL_PHONE="dial_phone";
	public static String PAGE_ABOUTUS="page_aboutus";
	
	public static String PAGE_INTRO="page_intro";
	public static String FD_PAGE_INTRO="fd_page_intro";
	public static String PAGE_PRODUCT_DETAIL="page_product_detail";
	
	public static String PAGE_QRCODE="page_qrcode";
	public static String PAGE_WISDOMMAP="page_wisdommap";

	public static String SCENERYDETAIL="editscenery";
	public static String FOODDETAIL="editfood";
	public static String DEFAULT_PIC="default.png";
	public static final String UPLOAD_DIR = "upload";
	
	// 微旅游----附件上传虚拟路径(tomcat中'D:/yzywUploads'虚拟成'/yzywPath')
	public static final String QIANQIOU_PATH = "/qianqiuPath";		
	
	public static String CHECKCODE_NOT_EXIT="checkcode_not_exit";
	
	public static String CHECKLOGO_NOT_EXIT="checklogo_not_exit";
	
	public static String CHECKCODE_HAS_CHECKED="checkcode_has_checked";
	//上传图片超过这个大小需要裁减
	public static long max_pic =(long) 512000;
	public static String PAGE_SCENERY="page_scenery";
	public static String PAGE_SCENERYPIC="page_scenerypic";
	public static String PAGE_ANNOUNCEMENT_SCENERYPIC="page_announcement_pic";
	public static String PAGE_ANNOUNCEMENT_PIC="announcement_detail";

	public static String PAGE_VOICE="page_voice";
	public static String PAGE_VIDEO="page_video";
	public static String PAGE_SCENERY_LIST="page_scenery_list";
	public static String PAGE_SCENERY_DETAIL="page_scenery_detail";
	public static String PAGE_VIDEO2="page_video2";
	public static String PAGE_ERROR="page_error";
	public static String PAGE_ERROR2="page_error2";
	public static String PAGE_FORWARD="page_forward";
	public static String PAGE_HOME="page_home";
	public static String PAGE_DOOR="page_door";
	public static String PAGE_TO_HERE="page_to_here";
	
	
}
