package order.service;

import java.util.List;

import net.sf.json.JSONArray;
import order.db.DAO.PartnersDao;
import order.db.DDO.PartnersDDO;

public class PartnersService {

	private PartnersDao partnersDao;

	public PartnersDao getPartnersDao() {
		return partnersDao;
	}

	public void setPartnersDao(PartnersDao partnersDao) {
		this.partnersDao = partnersDao;
	}

	/**
	 * 返回JSON格式
	 * 
	 * @return
	 */
	public String getAnnouncementByStoreId(String name) {
		List<PartnersDDO> ret;
		ret = partnersDao.getAnnouncementByName(name);
		if (ret == null || ret.size() == 0)
			return "{\"total\":0,\"rows\":[]}";

		JSONArray jsonArray = JSONArray.fromObject(ret);
		String sceneryStr = jsonArray.toString();
		sceneryStr = "{\"total\":" + ret.size() + ",\"rows\":" + sceneryStr
				+ "}";
		return sceneryStr;

	}

	public PartnersDDO getAnnouncementById(int Id) {
		PartnersDDO ret;
		ret = partnersDao.get(Id);
		if (ret == null)
			return null;
		return ret;
	}

	public PartnersDDO deleteById(int id) {
		PartnersDDO ddo = new PartnersDDO();
		ddo.setId(id);
		 partnersDao.delPartners(ddo);
		return ddo;
	}

	public PartnersDDO savePartners(PartnersDDO ddo) {
		partnersDao.saveOrUpdate(ddo);
		return ddo;
	}


}
