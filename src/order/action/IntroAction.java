package order.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.db.DDO.IntroDDO;
import order.service.IntroService;
import order.service.ProductService;
import order.web.DDO.ProductWebDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;
import order.web.util.PropertiesUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class IntroAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());
	private IntroService introService;
	private ProductService productService;
	private int id;
	private String type;
	private String detail;

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public IntroAction() {
		introService = (IntroService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "introService");
		productService = (ProductService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "productService");
	}

	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		boolean ret = introService.saveOrUpdate(id, type, detail,
				storeDDO.getId());
		if (ret)
			response.getWriter().print(Constant.SUCCESS);
		else
			response.getWriter().print(
					PropertiesUtil.getProperties(Constant.COMMON_ERROR));
		return null;
	}

	/**
	 * PC后台管理
	 * 千秋建筑装饰
	 */
	public String get() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			return Constant.PAGE_INTRO;
		}
		IntroDDO ddo = null;
		ddo = introService.get(storeDDO.getId(), type);
		if (ddo == null) {
			ddo = new IntroDDO();
			ddo.setType(type);
		}
		request.setAttribute("intro", ddo);
		return Constant.PAGE_INTRO;
	}

	/**
	 * 微信端
	 * 千秋建筑装饰
	 */
	public String fdget() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// if(!isLegalOp(request))return Constant.PAGE_ERROR;
		IntroDDO ddo = null;
		ddo = introService.get(1, type);
		request.setAttribute("fdintro", ddo);

		// List<ProductWebDDO> productList=
		// productService.getProductsByStoreId(id);
		// request.setAttribute("productList", productList);

		return Constant.FD_PAGE_INTRO;
	}

	public String productDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		// if(!isLegalOp(request))return Constant.PAGE_ERROR;
		IntroDDO ddo = null;
		ddo = introService.get(1, type);
		request.setAttribute("fdintro", ddo);

		List<ProductWebDDO> productList = productService
				.getProductsByStoreId(id);
		request.setAttribute("productList", productList);

		return Constant.FD_PAGE_INTRO;
	}
}