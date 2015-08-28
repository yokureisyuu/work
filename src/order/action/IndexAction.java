package order.action;

import javax.servlet.http.HttpServletRequest;

import order.db.DDO.TheWayDDO;
import order.service.StoreService;
import order.service.TheWayService;
import order.web.util.Constant;
import order.web.util.MethodUtil;
import order.web.util.PropertiesUtil;

import org.apache.struts2.ServletActionContext;

/**
 * 页面分发类
 * 
 * @author Administrator
 * 
 */
public class IndexAction extends BaseAction {
	private StoreService storeService;
	private TheWayService theWayService;
	public IndexAction() {
		storeService = (StoreService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "storeService");
		
		theWayService = (TheWayService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "theWayService");
	}

	public String execute() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		/***********************************************************************
		 * storeDDO =storeService.loginStore("da");
		 * if(MethodUtil.isNotNull(storeDDO.getMessage())){
		 * request.setAttribute("errorinfo", storeDDO.getMessage()); return
		 * Constant.PAGE_ERROR2; } UserDDO userDDO
		 * =userService.login("15801939370","1234567");
		 * request.getSession().setAttribute("store",storeDDO);
		 * request.getSession().setAttribute("user",userDDO);
		 **********************************************************************/
		String error = isLegalUserOpForPage(request);
		if (error == null)
			return Constant.PAGE_INDEX;
		else
			return error;
	}

	public String home() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		clearStoreSession(request);
		String domain = request.getServletPath();
		int index = domain.indexOf("/indexhome_");
		if (index < 0) {
			request.setAttribute("errorinfo",
					PropertiesUtil.getProperties(Constant.STORE_LOGIN_ERROR));
			return Constant.PAGE_ERROR;
		}
		domain = domain.substring(index + 11);
		storeDDO = storeService.loginStore(domain);

		if (MethodUtil.isNotNull(storeDDO.getMessage())) {
			request.setAttribute("errorinfo", storeDDO.getMessage());
			return Constant.PAGE_ERROR;
		}
		request.getSession().setAttribute("store", storeDDO);
		request.setAttribute("store", storeDDO);
		return Constant.PAGE_FORWARD;
	}
	/**
	 * PC门户网站
	 * @return
	 * @throws Exception
	 */
	public String door() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		clearStoreSession(request);
		String domain = request.getServletPath();
		int index = domain.indexOf("/indexdoor_");
		if (index < 0) {
			request.setAttribute("errorinfo",
					PropertiesUtil.getProperties(Constant.STORE_LOGIN_ERROR));
			return Constant.PAGE_ERROR;
		}
		String[] arrayBuf=domain.split("_");
		if (arrayBuf.length==2){
			domain = arrayBuf[1];
		}
		storeDDO = storeService.loginStore(domain);

		if (MethodUtil.isNotNull(storeDDO.getMessage())) {
			request.setAttribute("errorinfo", storeDDO.getMessage());
			return Constant.PAGE_ERROR;
		}
		request.getSession().setAttribute("store", storeDDO);
		request.setAttribute("store", storeDDO);
		return Constant.PAGE_DOOR;
	}

	public String forward() throws Exception {
		return Constant.PAGE_HOME;
	}
	
	/**
	 * 一键拨号
	 * @return
	 */
	public String dialPhone() {
		HttpServletRequest request = ServletActionContext.getRequest();

		String domain =request.getServletPath();
		int index =domain.indexOf("/dialPhone");
		if(index<0){
			request.setAttribute("errorinfo", PropertiesUtil.getProperties(Constant.STORE_LOGIN_ERROR));
			return Constant.PAGE_ERROR2;
		}
		domain = domain.substring(index+11);
		storeDDO =storeService.loginStore(domain);
		
		if(MethodUtil.isNotNull(storeDDO.getMessage())){
			request.setAttribute("errorinfo", storeDDO.getMessage());
			return Constant.PAGE_ERROR;
		}
		request.getSession().setAttribute("store",storeDDO);
		request.setAttribute("store",storeDDO);		
		return Constant.DIAL_PHONE;
	}
	
	/**
	 * 关于我们
	 * @return
	 * @throws Exception
	 */
	public String aboutus() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		String domain =request.getServletPath();
		int index =domain.indexOf("/aboutus");
		if(index<0){
			if(!isLegalOp(request)){
				request.setAttribute("errorinfo", PropertiesUtil.getProperties(Constant.STORE_LOGIN_ERROR));
				return Constant.PAGE_ERROR2;			
			}else{
				domain=storeDDO.getStoreDomain();
			}
		}else{
			domain = domain.substring(index+9);
		}
		
		storeDDO =storeService.loginStore(domain);
		
		if(MethodUtil.isNotNull(storeDDO.getMessage())){
			request.setAttribute("errorinfo", storeDDO.getMessage());
			return Constant.PAGE_ERROR;
		}
		
		TheWayDDO ddo = theWayService.getTheWay(storeDDO.getId());
		request.setAttribute("fdway", ddo);
		
		request.getSession().setAttribute("store",storeDDO);
		request.setAttribute("store",storeDDO);
		return Constant.PAGE_ABOUTUS;
	}
}