package order.service;

import order.db.DAO.IntroDAO;
import order.db.DDO.IntroDDO;

public class IntroService {

	private IntroDAO introDAO;

	public IntroDAO getIntroDAO() {
		return introDAO;
	}

	public void setIntroDAO(IntroDAO introDAO) {
		this.introDAO = introDAO;
	}

	public boolean saveOrUpdate(int id,String type,String detail,int storeId ) {
		IntroDDO introDDO = new IntroDDO();
		introDDO.setType(type);
		introDDO.setDetail(detail);
		introDDO.setStoreId(storeId);
		
		if(id!=0)introDDO.setId(id);
		return introDAO.saveOrUpdate(introDDO);
	}

	public IntroDDO get(int storeId,String type){
		return introDAO.get(storeId,type);
	}
	
}
