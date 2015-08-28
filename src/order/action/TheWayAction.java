package order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.db.DDO.TheWayDDO;
import order.service.ProductService;
import order.service.TheWayService;
import order.web.DDO.ProductWebDDO;
import order.web.util.Constant;
import order.web.util.HttpUtils;
import order.web.util.MethodUtil;
import order.web.util.PropertiesUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class TheWayAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private int id;
	private String latitude;
	private String longitude;
	private String address;
	private String detail;
	private TheWayService theWayService;
	private ProductService productService;

	public TheWayAction() {

		theWayService = (TheWayService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "theWayService");
		productService = (ProductService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "productService");

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		boolean ret = theWayService.saveOrUpdate(id, latitude, longitude,
				address, detail, storeDDO.getId());
		if (ret)
			response.getWriter().print(Constant.SUCCESS);
		else
			response.getWriter().print(
					PropertiesUtil.getProperties(Constant.COMMON_ERROR));
		return null;
	}

	public String parseAddress() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String data = HttpUtils.get(
				"http://api.map.baidu.com/geocoder/v2/?address=" + address
						+ "&output=json&ak=pL8Kcy5vZ8XphLPC8epkAhla", null);
		response.getWriter().print(data);
		return null;
	}

	/**
	 * 微信端交通指引
	 * @return
	 * @throws Exception
	 */
	public String toHere() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if (!isLegalOp(request))
			return Constant.PAGE_ERROR;
		TheWayDDO ddo = theWayService.getTheWay(storeDDO.getId());
		request.setAttribute("fdway", ddo);

		List<ProductWebDDO> productList = productService
				.getProductsByStoreId(39);

		// request.setAttribute("sceneryone",ret);
		request.setAttribute("productList", productList);

		return Constant.PAGE_TO_HERE;
	}

	/**
	 * PC后台管理端
	 * 交通地址路线规划
	 * @return
	 * @throws Exception
	 */
	public String get() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			return Constant.PAGE_WAY;
		}
		TheWayDDO ddo = theWayService.getTheWay(storeDDO.getId());
		if (ddo != null)
			request.setAttribute("way", ddo);
		return Constant.PAGE_WAY;
	}
}