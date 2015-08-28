package order.db.DAO;

import java.util.ArrayList;
import java.util.List;

import order.db.DDO.SceneryDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SceneryDao {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@SuppressWarnings("unchecked")
	public List<SceneryDDO> getScenerysByStoreId(int storeId,String sceneryName) {
		List<SceneryDDO> ddoList;
		try {
			String str="";
			if(MethodUtil.isNotNull(sceneryName))str= " and sceneryName like '%" + sceneryName + "%'";
			final String hql = "from SceneryDDO where storeId=" + storeId+str+"order by displayOrder desc";
			ddoList = getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ddoList;
	}

	public SceneryDDO insertScenery(SceneryDDO sceneryDDO) {

		try {
			int row = (Integer) this.getHibernateTemplate().save(sceneryDDO);
			sceneryDDO.setId(row);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return sceneryDDO;
	}

	public boolean delScenery(SceneryDDO sceneryDDO) {
		try {
			this.getHibernateTemplate().delete(sceneryDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateScenery(SceneryDDO sceneryDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(sceneryDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 增加风景时调用
	 * @param SceneryDDO
	 * @return
	 */
	public SceneryDDO isSceneryExit(SceneryDDO sceneryDDO) {
		String hql = "from SceneryDDO m where m.storeId=? And m.sceneryName=?";
		@SuppressWarnings("rawtypes")
		ArrayList meyTypeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { sceneryDDO.getStoreId(), sceneryDDO.getSceneryName()});
		if (meyTypeDDOList != null && meyTypeDDOList.size() > 0) {
			sceneryDDO = (SceneryDDO) meyTypeDDOList.get(0);
			sceneryDDO.setMessage(Constant.SCENERY_ADD_REPEAT_ERROR);
		}
		return sceneryDDO;
	}
	/**
	 * 修改房间时调用
	 * @param SceneryDDO
	 * @return
	 */
	public SceneryDDO isSceneryExit2(SceneryDDO sceneryDDO) {
		String hql = "from SceneryDDO m where m.storeId=? And m.sceneryName=? And m.id<>?";
		@SuppressWarnings("rawtypes")
		ArrayList meyTypeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { sceneryDDO.getStoreId(), sceneryDDO.getSceneryName(), sceneryDDO.getId() });
		if (meyTypeDDOList != null && meyTypeDDOList.size() > 0) {
			sceneryDDO = (SceneryDDO) meyTypeDDOList.get(0);
			sceneryDDO.setMessage(Constant.SCENERY_ADD_REPEAT_ERROR);
		}
		return sceneryDDO;
	}
	public SceneryDDO get(int id) {
		String hql = "from SceneryDDO m where m.id=?";
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {id});
		if (retList != null && retList.size() > 0) {
			SceneryDDO  sceneryDDO= (SceneryDDO) retList.get(0);
			return sceneryDDO;
		}
		return null;
		
	}
	
	public boolean saveOrUpdate(SceneryDDO sceneryDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(sceneryDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
