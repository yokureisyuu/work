package order.db.DDO;

import order.web.util.PropertiesUtil;


public class TheWayDDO {

	private int id;
	private String latitude;
	private String longitude;
	private String address;
	private String detail;
	private int storeId;
	private String message;
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
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = PropertiesUtil.getProperties(message);
	}
	
	
}
