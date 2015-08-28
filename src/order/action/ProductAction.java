package order.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import order.service.ProductService;
import order.service.SceneryService;
import order.web.DDO.ProductWebDDO;
import order.web.DDO.SceneryWebDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.wechat.common.Config;
import com.wechat.sub.JsSdk;
import com.wechat.sub.Sign;

public class ProductAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private ProductService productService;
	private SceneryService sceneryService;
	private int id;

	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;

	private String linkUrl;
	private String imgUrl;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	/**
	 * 
	 */
	private String ticket;

	public ProductAction() {

		productService = (ProductService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "productService");
		sceneryService = (SceneryService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "sceneryService");
	}

	public String productDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// request.getRequestURI()
		// request.getQueryString()
		// request.getRequestURL()
		// sceneryHead

		SceneryWebDDO webDdo = sceneryService.getScenerysById(id);

		imgUrl = Constant.doMain + webDdo.getSceneryHead();

		request.setAttribute("sceneryone", webDdo);

		List<ProductWebDDO> productList = productService
				.getProductsByStoreId(id);
		request.setAttribute("productList", productList);

		JsSdk jsSdk = new JsSdk();
		ticket = jsSdk.ticket();

		setAppId(Config.instance().getAppid());

		String jsapi_ticket = ticket;

		// 注意 URL 一定要动态获取，不能 hardcode
		String url = Constant.doMain;
		// request.getRequestURL().toString();
		url = url + request.getRequestURI();
		String queryString = request.getQueryString().toString();
		if (!queryString.isEmpty()) {
			url = url + "?" + queryString;
		}

		Map<String, String> ret = Sign.sign(jsapi_ticket, url);
		for (@SuppressWarnings("rawtypes")
		Map.Entry entry : ret.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
			if (entry.getKey().equals("url")) {
				linkUrl = entry.getValue().toString();
			} else if (entry.getKey().equals("jsapi_ticket")) {
				// jsapi_ticket=entry.getValue().toString();
			} else if (entry.getKey().equals("timestamp")) {
				setTimestamp(entry.getValue().toString());
			} else if (entry.getKey().equals("nonceStr")) {
				setNonceStr(entry.getValue().toString());
			} else if (entry.getKey().equals("signature")) {
				setSignature(entry.getValue().toString());
			}
		}

		log.warn("productDetail:" + Constant.PAGE_PRODUCT_DETAIL);

		return Constant.PAGE_PRODUCT_DETAIL;
	}
}