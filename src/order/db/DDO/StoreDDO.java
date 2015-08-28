package order.db.DDO;

import order.web.util.PropertiesUtil;

public class StoreDDO {

	private int id;
	private String storeName;
	private String storeSwitchImage1;
	private String storeSwitchImage2;
	private String storeSwitchImage3;
	private String storeSwitchImage4;
	private String storeSwitchImage5;
	private int storeDiscount;
	private String storeDiscountInfo;
	private String storeComm;
	private String storeDomain;
	private String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStoreComm() {
		return storeComm;
	}
	public void setStoreComm(String storeComm) {
		this.storeComm = storeComm;
	}
	public int getStoreDiscount() {
		return storeDiscount;
	}
	public void setStoreDiscount(int storeDiscount) {
		this.storeDiscount = storeDiscount;
	}
	public String getStoreDiscountInfo() {
		return storeDiscountInfo;
	}
	public void setStoreDiscountInfo(String storeDiscountInfo) {
		this.storeDiscountInfo = storeDiscountInfo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getStoreSwitchImage1() {
		return storeSwitchImage1;
	}
	public void setStoreSwitchImage1(String storeSwitchImage1) {
		this.storeSwitchImage1 = storeSwitchImage1;
	}
	public String getStoreSwitchImage2() {
		return storeSwitchImage2;
	}
	public void setStoreSwitchImage2(String storeSwitchImage2) {
		this.storeSwitchImage2 = storeSwitchImage2;
	}
	public String getStoreSwitchImage3() {
		return storeSwitchImage3;
	}
	public void setStoreSwitchImage3(String storeSwitchImage3) {
		this.storeSwitchImage3 = storeSwitchImage3;
	}
	public String getStoreSwitchImage4() {
		return storeSwitchImage4;
	}
	public void setStoreSwitchImage4(String storeSwitchImage4) {
		this.storeSwitchImage4 = storeSwitchImage4;
	}
	public String getStoreSwitchImage5() {
		return storeSwitchImage5;
	}
	public void setStoreSwitchImage5(String storeSwitchImage5) {
		this.storeSwitchImage5 = storeSwitchImage5;
	}
	public String getStoreDomain() {
		return storeDomain;
	}
	public void setStoreDomain(String storeDomain) {
		this.storeDomain = storeDomain;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = PropertiesUtil.getProperties(message);
	}
	public String toString(){
		
		return "id："+id+",域名："+storeDomain+",店名："+storeName+",错误信息："+message==null?"无":message;
	}
}

