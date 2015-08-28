package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.db.DDO.AdminUserDDO;
import order.service.AdminUserService;
import order.service.StoreService;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class AdminUserAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private int id;

	private String phone;

	private String password;

	private String username;

	private String address;

	private AdminUserService adminUserService;
	private StoreService storeService;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public AdminUserAction() {

		adminUserService = (AdminUserService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "adminUserService");
		storeService = (StoreService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "storeService");

	}

	public String login() throws Exception {

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		AdminUserDDO userDDO = adminUserService.login(username, password);
		if (MethodUtil.isNotNull(userDDO.getMessage())) {
			log.info("用户登陆失败," + userDDO);
			response.getWriter().print(userDDO.getMessage());
		} else {
			log.info("用户登陆成功," + userDDO);
			HttpServletRequest request = ServletActionContext.getRequest();
			storeDDO = storeService.loginStore2(userDDO.getUserStoreId());
			if (MethodUtil.isNotNull(storeDDO.getMessage())) {
				request.setAttribute("errorinfo", storeDDO.getMessage());
				response.getWriter().print(Constant.STORE_LOGIN_ERROR);
			}
			request.getSession().setAttribute("store", storeDDO);
			request.getSession().setAttribute("user", userDDO);
			response.getWriter().print(Constant.SUCCESS);
		}

		return null;
	}

	/**
	 * 在用户中心点击退出时用到
	 * 
	 * @return
	 * @throws Exception
	 */
	public String exit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		clearAllSession(request);
		response.getWriter().print(Constant.SUCCESS);
		return null;
	}

	/**
	 * 在用户中心点击退出时用到
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyPwd() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		AdminUserDDO adminUserDDO = adminUserService.updateUser(userDDO,
				username, phone, password);
		if (MethodUtil.isNotNull(adminUserDDO.getMessage())) {
			response.getWriter().print(adminUserDDO.getMessage());
			return null;
		}
		request.getSession().setAttribute("user", adminUserDDO);
		response.getWriter().print(Constant.SUCCESS);
		return null;
	}

}