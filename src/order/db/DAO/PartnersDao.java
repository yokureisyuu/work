package order.db.DAO;

import java.util.ArrayList;
import java.util.List;

import order.db.DDO.PartnersDDO;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class PartnersDao {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@SuppressWarnings("unchecked")
	public List<PartnersDDO> getAnnouncementByName(String name) {
		List<PartnersDDO> ddoList;
		try {
			String str = "";
			if (MethodUtil.isNotNull(name)){
				str = " where name='" + name + "'";
			}
			
			final String hql = "from PartnersDDO " + str + " order by id desc";
			ddoList = getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ddoList;
	}

	public PartnersDDO insertAnnouncement(PartnersDDO ddo) {

		try {
			int row = (Integer) this.getHibernateTemplate().save(ddo);
			ddo.setId(row);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ddo;
	}

	public boolean delPartners(PartnersDDO ddo) {
		try {
			this.getHibernateTemplate().delete(ddo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updatePartners(PartnersDDO ddo) {
		try {
			this.getHibernateTemplate().saveOrUpdate(ddo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}



	public PartnersDDO get(int id) {
		String hql = "from PartnersDDO m where m.id=?";
		
		
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql,
				new Object[] { id });
		if (retList != null && retList.size() > 0) {
			PartnersDDO ddo = (PartnersDDO) retList.get(0);
			return ddo;
		}
		return null;

	}

	public boolean saveOrUpdate(PartnersDDO ddo) {
		try {
			this.getHibernateTemplate().saveOrUpdate(ddo);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
