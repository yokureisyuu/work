package order.db.DAO;

import java.util.ArrayList;
import java.util.List;

import order.db.DDO.StoreDDO;

import order.web.util.Constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class StoreDao {
	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}


	public boolean updateStore(StoreDDO storeDDO){
		 try {
			this.getHibernateTemplate().saveOrUpdate(storeDDO);
			return true;
		} catch (DataAccessException e) {
			//  Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public StoreDDO isStoreNameExit(StoreDDO storeDDO){
		String hql = "from StoreDDO s where s.storeName=? and id<>?";
		@SuppressWarnings("rawtypes")
		ArrayList storeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { storeDDO.getStoreName(),storeDDO.getId()});
		if (storeDDOList != null && storeDDOList.size() > 0) {
			storeDDO = (StoreDDO) storeDDOList.get(0);
			storeDDO.setMessage(Constant.STORE_MODIFY_NAME_ERROR);
		}
		return storeDDO;
	}
	/**
	 * 根据域名登陆不同的商店，取出商店信息
	 * @param storeDDO
	 * @return
	 */
	public StoreDDO loginStore(StoreDDO storeDDO) {

		String hql = "from StoreDDO u where u.storeDomain=?";
		
		List<?> storeDDOList =  this.getHibernateTemplate().find(hql, new Object[]{storeDDO.getStoreDomain()} );
		if (storeDDOList != null && storeDDOList.size() > 0)
			return (StoreDDO) storeDDOList.get(0);
		storeDDO.setMessage(Constant.STORE_LOGIN_ERROR);
		return storeDDO;
	} 
	public StoreDDO loginStore2(StoreDDO storeDDO) {
		String hql = "from StoreDDO u where u.id=?";
		@SuppressWarnings("rawtypes")
		ArrayList storeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {  storeDDO.getId()});
		if (storeDDOList != null && storeDDOList.size() > 0)
			return (StoreDDO) storeDDOList.get(0);
		storeDDO.setMessage(Constant.STORE_LOGIN_ERROR);
		return storeDDO;
	} 
}
