package order.service;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import order.db.DAO.UserDao;
import order.db.DDO.UserDDO2;
import order.web.DDO.UserWebDDO;
import order.web.util.MethodUtil;

public class UserService {

	private UserDao userDao;

	public String getAllUser(int storeId,String username,String phone, int page, int row){
		int total = userDao.getTotalSize(storeId,username, phone);
		if(total==0){
			return "{\"total\":0,\"rows\":[]}";
		}
		List<UserDDO2> ret =userDao.getAllUser(storeId,username,phone, page, row);
		if (ret == null || ret.size() == 0)
			return "{\"total\":0,\"rows\":[]}";
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<UserWebDDO>webDDOList = new ArrayList();
		for(int i=0;i<ret.size();i++){
			UserDDO2 ddo = ret.get(i);
			UserWebDDO webDDO = new UserWebDDO();
			
			webDDO.setCreateDate(MethodUtil.formatDateTime(ddo.getCreateDate()));
			webDDO.setId(ddo.getId());
			webDDO.setName(ddo.getName());
			webDDO.setPhone(ddo.getPhone());
			webDDOList.add(webDDO);
		}
		JSONArray jsonArray = JSONArray.fromObject(webDDOList);
		String webString = jsonArray.toString();
		webString = "{\"total\":" + total + ",\"rows\":" + webString + "}";
		return webString;
		
	}


	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
