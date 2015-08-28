package order.service;

import order.db.DAO.StoreDao;
import order.db.DDO.StoreDDO;
public class StoreService {

	private StoreDao storeDao;

	public StoreDao getStoreDao() {
		return storeDao;
	}

	public void setStoreDao(StoreDao storeDao) {
		this.storeDao = storeDao;
	}
	public StoreDDO valideAddStore(StoreDDO storeDDO){
		return storeDDO;
	}

	public boolean updateStore(StoreDDO storeDDO){
		 return storeDao.updateStore(storeDDO);
	}

	public StoreDDO  valideStoreName(String storeName){
		StoreDDO storeDDO = new StoreDDO();
		storeDDO.setStoreName(storeName);
		return  storeDao.isStoreNameExit(storeDDO);	
	}
	public StoreDDO loginStore(String storeDomain){
		StoreDDO storeDDO = new StoreDDO();
		storeDDO.setStoreDomain(storeDomain);
		return storeDao.loginStore(storeDDO);
	}
	public StoreDDO loginStore2(int storeId){
		StoreDDO storeDDO = new StoreDDO();
		storeDDO.setId(storeId);
		return storeDao.loginStore2(storeDDO);
	}
}
