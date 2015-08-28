package order.db.DDO;

import java.util.Date;

public class SceneryDDO {

	private int id;

	private String sceneryName;
	private String sceneryHead;
	private String sceneryDesc;
	private String sceneryDetail;
	private String sceneryPic1;
	private String sceneryPic2;
	private String sceneryPic3;
	private String sceneryPic4;
	private String sceneryPic5;	
	private int storeId;
	private Date updateDate;
	private String message;
	private String qrCodeVideoPic;
	private String qrCodeVoicePic;
	private String video;
	private String voice;
	private String displayOrder;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSceneryName() {
		return sceneryName;
	}
	public void setSceneryName(String sceneryName) {
		this.sceneryName = sceneryName;
	}
	public String getSceneryHead() {
		return sceneryHead;
	}
	public void setSceneryHead(String sceneryHead) {
		this.sceneryHead = sceneryHead;
	}
	public String getSceneryDesc() {
		return sceneryDesc;
	}
	public void setSceneryDesc(String sceneryDesc) {
		this.sceneryDesc = sceneryDesc;
	}
	public String getSceneryDetail() {
		return sceneryDetail;
	}
	public void setSceneryDetail(String sceneryDetail) {
		this.sceneryDetail = sceneryDetail;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getQrCodeVideoPic() {
		return qrCodeVideoPic;
	}
	public void setQrCodeVideoPic(String qrCodeVideoPic) {
		this.qrCodeVideoPic = qrCodeVideoPic;
	}
	public String getQrCodeVoicePic() {
		return qrCodeVoicePic;
	}
	public void setQrCodeVoicePic(String qrCodeVoicePic) {
		this.qrCodeVoicePic = qrCodeVoicePic;
	}
	public String getVideo() {
		return video;
	}
	public void setVideo(String video) {
		this.video = video;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getSceneryPic1() {
		return sceneryPic1;
	}
	public void setSceneryPic1(String sceneryPic1) {
		this.sceneryPic1 = sceneryPic1;
	}
	public String getSceneryPic2() {
		return sceneryPic2;
	}
	public void setSceneryPic2(String sceneryPic2) {
		this.sceneryPic2 = sceneryPic2;
	}
	public String getSceneryPic3() {
		return sceneryPic3;
	}
	public void setSceneryPic3(String sceneryPic3) {
		this.sceneryPic3 = sceneryPic3;
	}
	public String getSceneryPic4() {
		return sceneryPic4;
	}
	public void setSceneryPic4(String sceneryPic4) {
		this.sceneryPic4 = sceneryPic4;
	}
	public String getSceneryPic5() {
		return sceneryPic5;
	}
	public void setSceneryPic5(String sceneryPic5) {
		this.sceneryPic5 = sceneryPic5;
	}
	public String getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(String displayOrder) {
		this.displayOrder = displayOrder;
	}
}
