package order.db.DAO;

import java.util.ArrayList;

import order.db.DDO.IntroDDO;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class IntroDAO {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	public boolean saveOrUpdate(IntroDDO introDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(introDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	public IntroDDO get(int storeId,String type) {
		String hql = "from IntroDDO m where m.storeId="+storeId+" and m.type="+type;
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql);
		if (retList != null && retList.size() > 0) {
			IntroDDO  introDDO= (IntroDDO) retList.get(0);
			return introDDO;
		}
		return null;
		
	}	
}
