package order.service;

import order.db.DAO.TheWayDAO;
import order.db.DDO.TheWayDDO;

public class TheWayService {

	private TheWayDAO theWayDAO;

	public TheWayDAO getTheWayDAO() {
		return theWayDAO;
	}

	public void setTheWayDAO(TheWayDAO theWayDAO) {
		this.theWayDAO = theWayDAO;
	}
	public boolean saveOrUpdate(int id,String latitude,String longitude,String address,String detail,int storeId ) {
		TheWayDDO theWayDDO = new TheWayDDO();
		theWayDDO.setAddress(address);
		theWayDDO.setDetail(detail);
		theWayDDO.setLatitude(latitude);
		theWayDDO.setLongitude(longitude);
		theWayDDO.setStoreId(storeId);
		if(id!=0)theWayDDO.setId(id);
		return theWayDAO.saveOrUpdate(theWayDDO);
	}

	public TheWayDDO getTheWay(int storeId){
		return theWayDAO.getTheWay(storeId);
	}
}
