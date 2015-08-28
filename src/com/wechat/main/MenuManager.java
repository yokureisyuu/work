package com.wechat.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wechat.AccessToken;
import com.wechat.Button;
import com.wechat.CommonUtil;
import com.wechat.ComplexButton;
import com.wechat.Menu;
import com.wechat.MenuUtil;
import com.wechat.SubButton;

/**
 * 菜单管理器类
 * 
 * @author tiangai
 * @since 2014-06-30 Am11:48
 * @version 1.0
 */
public class MenuManager extends CommonUtil {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);

	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	private static Menu getMenu() {
		// 苏州市千秋建筑装饰有限公司
		String homePageUrl = "http://m.qianqiusz.56jyt.com/qianqiu/";// 域名
		String storeDomain = "qq";// 域名
		storeDomain = "_" + storeDomain;

		ComplexButton mainBtn1 = new ComplexButton();
		mainBtn1.setName("微官网");
		mainBtn1.setType(MENU_CLICK);
		mainBtn1.setKey("1+");

		ComplexButton mainBtn2 = new ComplexButton();
		mainBtn2.setName("千秋历史");
		mainBtn2.setType(MENU_CLICK);
		mainBtn2.setKey("2+");

		ComplexButton mainBtn3 = new ComplexButton();
		mainBtn3.setName("关于千秋");
		mainBtn3.setType(MENU_CLICK);
		mainBtn3.setKey("3+");

		SubButton btn11 = new SubButton();
		btn11.setName("首页");
		btn11.setType(MENU_VIEW);
		btn11.setKey("11");
		btn11.setUrl(homePageUrl + "indexhome" + storeDomain);

		SubButton btn12 = new SubButton();
		btn12.setName("公司团队");
		btn12.setType(MENU_VIEW);
		btn12.setUrl(homePageUrl + "announcementgetDetail?id=3");

		SubButton btn13 = new SubButton();
		btn13.setName("工作流程");
		btn13.setType(MENU_VIEW);
		btn13.setUrl(homePageUrl + "announcementgetDetail?id=8");

		SubButton btn14 = new SubButton();
		btn14.setName("千秋的主要业绩");
		btn14.setType(MENU_VIEW);
		btn14.setUrl(homePageUrl + "announcementgetDetail?id=5");

		SubButton btn15 = new SubButton();
		btn15.setName("服务企业与合作伙伴");
		btn15.setType(MENU_VIEW);
		btn15.setUrl(homePageUrl + "announcementgetDetail?id=6");

		mainBtn1.setSub_button(new SubButton[] { btn11, btn12, btn13, btn14,
				btn15 });

		SubButton btn21 = new SubButton();
		btn21.setName("组织机构");
		btn21.setType(MENU_VIEW);
		btn21.setUrl(homePageUrl + "announcementgetDetail?id=9");

		SubButton btn22 = new SubButton();
		btn22.setName("千秋大事记");
		btn22.setType(MENU_VIEW);
		btn22.setKey("bsbgyrtg");
		btn22.setUrl(homePageUrl + "introfdget.action?type=3");

		SubButton btn23 = new SubButton();
		btn23.setName("公司资质与荣誉");
		btn23.setType(MENU_VIEW);
		btn23.setUrl(homePageUrl + "announcementgetDetail?id=1");

		SubButton btn24 = new SubButton();
		btn24.setName("与业主(甲方)合影");
		btn24.setType(MENU_VIEW);
		btn24.setUrl(homePageUrl + "announcementgetDetail?id=7");

		SubButton btn25 = new SubButton();
		btn25.setName("千秋的品牌建设和社会责任");
		btn25.setType(MENU_VIEW);
		btn25.setUrl(homePageUrl + "announcementgetDetail?id=4");

		mainBtn2.setSub_button(new SubButton[] { btn21, btn22, btn23, btn24,
				btn25 });

		SubButton btn31 = new SubButton();
		btn31.setName("公司简介");
		btn31.setType(MENU_VIEW);
		btn31.setKey("yzyw");
		btn31.setUrl(homePageUrl + "introfdget.action?type=1");

		SubButton btn32 = new SubButton();
		btn32.setName("公司环境");
		btn32.setType(MENU_VIEW);
		btn32.setUrl(homePageUrl + "announcementgetDetail?id=2");

		SubButton btn33 = new SubButton();
		btn33.setName("公司地址");
		btn33.setType(MENU_VIEW);
		btn33.setKey("bsbgyrtg");
		btn33.setUrl(homePageUrl + "aboutus" + storeDomain);

		SubButton btn34 = new SubButton();
		btn34.setName("一键拨号");
		btn34.setType(MENU_VIEW);
		btn34.setUrl(homePageUrl + "dialPhone" + storeDomain);

		SubButton btn35 = new SubButton();
		btn35.setName("总经理致辞");
		btn35.setType(MENU_VIEW);
		btn35.setUrl(homePageUrl + "introfdget.action?type=4");

		mainBtn3.setSub_button(new SubButton[] { btn31, btn32, btn33, btn34,
				btn35 });

		/**
		 * 每个一级菜单都有二级菜单项 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义： menu.s etButton(new
		 * Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(new Button[] { mainBtn1, mainBtn2, mainBtn3 });
		return menu;
	}

	/**
	 * 目前自定义菜单最多包括3个一级菜单， 每个一级菜单最多包含5个二级菜单。 一级菜单最多4个汉字，二级菜单最多7个汉字，
	 * 多出来的部分将会以“...”代替。 请注意，创建自定义菜单后， 由于微信客户端缓存，需要24小时微信客户端才会展现出来。
	 * 建议测试时可以尝试取消关注公众账号后再次关注， 则可以看到创建后的效果。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// 第三方用户唯一凭证
		String appId = WEIXIN_APPID;
		// 第三方用户唯一凭证密钥
		String appSecret = WEIXIN_APPSECRET;
		// 调用接口获取access_token
		AccessToken at = CommonUtil.getAccessToken(appId, appSecret);

		if (null != at) {
			// 调用接口创建菜单
			boolean result = MenuUtil.deleteMenu(at.getAccesstoken());
			// 判断删除结果
			if (result)
				log.info("删除菜单成功！ok");
			else
				log.info("删除菜单失败，错误码：" + result);

			result = MenuUtil.createMenu(getMenu(), at.getAccesstoken());
			// 判断菜单创建结果
			if (result)
				log.info("菜单创建成功！ok");
			else
				log.info("菜单创建失败，错误码：" + result);
		}
	}
}