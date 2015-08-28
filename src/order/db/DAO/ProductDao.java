package order.db.DAO;

import java.util.ArrayList;
import java.util.List;

import order.db.DDO.ProductDDO;
import order.web.util.Constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ProductDao {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}


	@SuppressWarnings("unchecked")
	public List<ProductDDO> getProductsByStoreId(int storeId) {
		List<ProductDDO> ddoList;
		try {
			
			final String hql = "from ProductDDO where storeId=" + storeId+" order by displayOrder ";
			ddoList = getHibernateTemplate().find(hql);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return ddoList;
	}

	public ProductDDO insertProduct(ProductDDO productDDO) {

		try {
			int row = (Integer) this.getHibernateTemplate().save(productDDO);
			productDDO.setId(row);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return productDDO;
	}

	public boolean delProduct(ProductDDO productDDO) {
		try {
			this.getHibernateTemplate().delete(productDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean updateProduct(ProductDDO productDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(productDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 增加风景时调用
	 * @param ProductDDO
	 * @return
	 */
	public ProductDDO isProductExit(ProductDDO productDDO) {
		String hql = "from ProductDDO m where m.storeId=? And m.sceneryName=?";
		@SuppressWarnings("rawtypes")
		ArrayList meyTypeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { productDDO.getStoreId(), productDDO.getDisplayOrder()});
		if (meyTypeDDOList != null && meyTypeDDOList.size() > 0) {
			productDDO = (ProductDDO) meyTypeDDOList.get(0);
			productDDO.setMessage(Constant.SCENERY_ADD_REPEAT_ERROR);
		}
		return productDDO;
	}
	/**
	 * 修改房间时调用
	 * @param ProductDDO
	 * @return
	 */
	public ProductDDO isProductExit2(ProductDDO productDDO) {
		String hql = "from ProductDDO m where m.storeId=? And m.sceneryName=? And m.id<>?";
		@SuppressWarnings("rawtypes")
		ArrayList meyTypeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { productDDO.getStoreId(), productDDO.getDisplayOrder(), productDDO.getId() });
		if (meyTypeDDOList != null && meyTypeDDOList.size() > 0) {
			productDDO = (ProductDDO) meyTypeDDOList.get(0);
			productDDO.setMessage(Constant.SCENERY_ADD_REPEAT_ERROR);
		}
		return productDDO;
	}

	
	public ProductDDO getProductByStoreIdAndDisplayOrder(int storeId,String displayOrder) {
		ProductDDO productDDO=null;
		String hql = "from ProductDDO m where m.storeId=? And m.displayOrder=?";
		@SuppressWarnings("rawtypes")
		ArrayList meyTypeDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {storeId, displayOrder});
		if (meyTypeDDOList != null && meyTypeDDOList.size() > 0) {
			productDDO = (ProductDDO) meyTypeDDOList.get(0);
			productDDO.setMessage(Constant.SCENERY_ADD_REPEAT_ERROR);
		}
		return productDDO;
	}
	
	
	public ProductDDO get(int id) {
		String hql = "from ProductDDO m where m.storeId=?";
		@SuppressWarnings("rawtypes")
		ArrayList retList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {id});
		if (retList != null && retList.size() > 0) {
			ProductDDO  productDDO= (ProductDDO) retList.get(0);
			return productDDO;
		}
		return null;
		
	}
	
	public boolean saveOrUpdate(ProductDDO productDDO) {
		try {
			this.getHibernateTemplate().saveOrUpdate(productDDO);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
