package order.db.DDO;

import java.util.Date;

/**
 * 网站信息DDO
 * 
 * @author Administrator
 *
 */
public class AnnouncementDDO {

	private int id;

	private String announcementName;

	private String announcementDesc;

	private Date announcementDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getAnnouncementDesc() {
		return announcementDesc;
	}

	public void setAnnouncementDesc(String announcementDesc) {
		this.announcementDesc = announcementDesc;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

}
