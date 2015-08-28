package order.db.DAO;

import java.util.ArrayList;

import order.db.DDO.TheWayDDO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class TheWayDAO {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public boolean updateTheWay(TheWayDDO theWayDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(theWayDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public boolean saveOrUpdate(TheWayDDO theWayDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(theWayDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public TheWayDDO getTheWay(int storeId) {
		String hql = "from TheWayDDO m where m.storeId=?";
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {storeId});
		if (retList != null && retList.size() > 0) {
			TheWayDDO  theWayDDO= (TheWayDDO) retList.get(0);
			return theWayDDO;
		}
		return null;
		
	}
}
