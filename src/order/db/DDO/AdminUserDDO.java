package order.db.DDO;

import java.util.Date;

import order.web.util.PropertiesUtil;

public class AdminUserDDO {

	private int id;
	private String name;
	private String password;
	private String phone;
	private Date createDate;
	private int userStoreId;
	private String message;
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getUserStoreId() {
		return userStoreId;
	}
	public void setUserStoreId(int userStoreId) {
		this.userStoreId = userStoreId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = PropertiesUtil.getProperties(message);
	}
	
	
}
