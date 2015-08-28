package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.db.DDO.PartnersDDO;
import order.service.PartnersService;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class PartnersAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private PartnersService partnersService;
	private PartnersDDO partners;

	public PartnersDDO getPartners() {
		return partners;
	}

	public void setPartners(PartnersDDO partners) {
		this.partners = partners;
	}

	public PartnersAction() {
		partnersService = (PartnersService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "partnersService");
	}

	/**
	 * Ajax返回JSON字符串,Menu页面中Ajax加载时用到
	 * 
	 * @return
	 * @throws Exception
	 */
	public String get() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String name = partners == null ? "" : partners.getName();
		String ret = partnersService.getAnnouncementByStoreId(name);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(ret);
		return null;
	}

	public String del() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		response.setContentType("text/html;charset=UTF-8");
		partnersService.deleteById(partners.getId());

		response.getWriter().print(Constant.SUCCESS);

		return null;
	}

	public String add() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");

		partnersService.savePartners(partners);

		response.getWriter().print(Constant.SUCCESS);
		return null;
	}

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modify() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		partnersService.savePartners(partners);
		response.getWriter().print(Constant.SUCCESS);
		return null;
	}
}