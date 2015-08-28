package order.service;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import order.db.DAO.AnnouncementDao;
import order.db.DDO.AnnouncementDDO;

public class AnnouncementService {

	private AnnouncementDao announcementDao;
	

	public AnnouncementDao getAnnouncementDao() {
		return announcementDao;
	}

	public void setAnnouncementDao(AnnouncementDao announcementDao) {
		this.announcementDao = announcementDao;
	}

	/**
	 * 返回JSON格式
	 * 
	 * @return
	 */
	public String getAnnouncementByStoreId(String sceneryName) {
		List<AnnouncementDDO> ret;
		ret = announcementDao.getAnnouncementByName(sceneryName);
		if (ret == null || ret.size() == 0)
			return "{\"total\":0,\"rows\":[]}";


		JSONArray jsonArray = JSONArray.fromObject(ret);
		String sceneryStr = jsonArray.toString();
		sceneryStr = "{\"total\":" + ret.size() + ",\"rows\":" + sceneryStr + "}";
		return sceneryStr;

	}

	
	public AnnouncementDDO getAnnouncementById(int Id) {
		AnnouncementDDO ret;
		ret = announcementDao.get(Id);
		if (ret == null)
			return null;
		
		return ret;

	}
	
	public Integer getPartnersNums() throws Exception {
		Integer num  = announcementDao.getPartnersNums();
		return num;

	}

	public AnnouncementDDO delAnnouncement(int id) {
		AnnouncementDDO announcementDdo = new AnnouncementDDO();
		announcementDdo.setId(id);
		 announcementDao.delAnnouncement(announcementDdo);

		return announcementDdo;
	}
	

	public AnnouncementDDO addAnnouncement(int id, String name, String desc) {
		AnnouncementDDO announcementDDO = new AnnouncementDDO();
		announcementDDO.setAnnouncementName(name);
		announcementDDO.setAnnouncementDesc(desc);
		announcementDDO.setAnnouncementDate(new Date());
		
		announcementDao.saveOrUpdate(announcementDDO);
		return announcementDDO;
	}

	



	public boolean saveOrUpdate(int id, String name) {
		AnnouncementDDO announcementDDO = announcementDao.get(id);
		if (announcementDDO == null)
			announcementDDO = new AnnouncementDDO();

		if (id != 0){
			announcementDDO.setId(id);
		}
		announcementDDO.setAnnouncementName(name);
		return announcementDao.saveOrUpdate(announcementDDO);
	}
	

	public boolean saveOrUpdate(AnnouncementDDO announcementDDO ) {
		return announcementDao.saveOrUpdate(announcementDDO);
	}
}
