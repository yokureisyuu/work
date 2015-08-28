package order.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.UserService;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class UserAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private int page;
	private int rows;
	private String phone;

	private String password;

	private String username;

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private UserService userService;

	public UserAction() {

		userService = (UserService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "userService");
	}

	/**
	 * 修改地址提交
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getAllUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String ret = userService.getAllUser(storeDDO.getId(), username, phone,
				page, rows);
		response.getWriter().print(ret);

		return null;
	}
}