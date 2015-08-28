package order.db.DAO;

import java.util.ArrayList;
import java.util.List;

import order.db.DDO.AnnouncementDDO;
import order.db.DDO.PartnersDDO;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AnnouncementDao {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<AnnouncementDDO> getAnnouncementByName(String announcementName) {
		List<AnnouncementDDO> ddoList;
		try {
			String str = "";
			if (MethodUtil.isNotNull(announcementName)){
				str = " where announcementName like '%" + announcementName + "%'";
			}
			
			final String hql = "from AnnouncementDDO " + str + " order by announcementDate desc";
			ddoList = getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ddoList;
	}

	public AnnouncementDDO insertAnnouncement(AnnouncementDDO announcementDDO) {

		try {
			int row = (Integer) this.getHibernateTemplate().save(announcementDDO);
			announcementDDO.setId(row);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return announcementDDO;
	}

	public boolean delAnnouncement(AnnouncementDDO announcementDDO) {
		try {
			this.getHibernateTemplate().delete(announcementDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateScenery(AnnouncementDDO announcementDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(announcementDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	public AnnouncementDDO get(int id) {
		String hql = "from AnnouncementDDO m where m.id=?";
		
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql,
				new Object[] { id });
		if (retList != null && retList.size() > 0) {
			AnnouncementDDO ddo = (AnnouncementDDO) retList.get(0);
			return ddo;
		}
		return null;

	}

	public boolean saveOrUpdate(AnnouncementDDO announcementDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(announcementDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	/**
	 * 获得已经购买的数量
	 * @return
	 */
	public Integer getPartnersNums() throws Exception {
		String hql = "from PartnersDDO as ddo";
		@SuppressWarnings("unchecked")
		List<PartnersDDO> retList =  this.getHibernateTemplate().find(hql);
		Integer num = 0;

		if (retList != null && retList.size() > 0) {
			for (PartnersDDO ddo : retList) {
				num = ddo.getNum()+num;
			}
			
		}
		return 100 - num;

	}
}
