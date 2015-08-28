package order.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import order.db.DAO.AdminUserDAO;
import order.db.DDO.AdminUserDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;

public class AdminUserService {

	private AdminUserDAO adminUserDAO;
	
	/**
	 * 验证注册用户信息
	 * 
	 * @param userDDO
	 * @return
	 */


	
	/**
	 * 验证登录用户信息
	 * @param userDDO
	 * @return
	 */
	private  AdminUserDDO valideLoginUser(AdminUserDDO userDDO){
		if (userDDO.getPassword().length() <6
				|| userDDO.getPassword().length() > 20) {
			userDDO.setMessage(Constant.USER_LOGIN_ERROR);
			return userDDO;
		} else {
			if (userDDO.getName().length()> 20||userDDO.getName().length()<2) {
				userDDO.setMessage(Constant.USER_LOGIN_ERROR);
				return userDDO;
			} 
		}
		return userDDO;
	}
	public  AdminUserDDO login(String userName, String userPassword) {
		AdminUserDDO userDDO = new AdminUserDDO();
		userDDO.setPassword(userPassword);
		//userDDO.setPhone(userPhone);
		userDDO.setName(userName);
		userDDO = valideLoginUser(userDDO);
		if (MethodUtil.isNotNull(userDDO.getMessage()))
			return userDDO;
		else return adminUserDAO.login(userDDO);
	}

	private  AdminUserDDO valideModiyPwd(AdminUserDDO userDDO){
		if (userDDO.getPassword().length() <6
				|| userDDO.getPassword().length() > 20) {
			userDDO.setMessage(Constant.USER_MODIFY_PWD_ERROR);
			return userDDO;
		} else {
			if (userDDO.getPhone().length() != 11) {
				userDDO.setMessage(Constant.USER_MODIFY_PHONE_ERROR);
				return userDDO;
			} else {
				String regEx = "^1\\d{10}$"; // 表示a或F
				Pattern pat = Pattern.compile(regEx);
				Matcher mat = pat.matcher(userDDO.getPhone());
				if (mat.matches()){
					userDDO.setMessage("");
					return userDDO;
				}else {
					userDDO.setMessage(Constant.USER_MODIFY_PHONE_ERROR);
					return userDDO;
				}
			}
		}
	}
	public AdminUserDDO updateUser(AdminUserDDO userDDO,String userName,String phone,String password){
		userDDO.setName(userName);
		userDDO.setPhone(phone);
		userDDO.setPassword(password);
		userDDO = valideModiyPwd(userDDO);
		if (MethodUtil.isNotNull(userDDO.getMessage()))
			return userDDO;
		userDDO=adminUserDAO.isPhoneExit(userDDO);
		if (MethodUtil.isNotNull(userDDO.getMessage()))
			return userDDO;
		userDDO=adminUserDAO.isNameExit(userDDO);
		if (MethodUtil.isNotNull(userDDO.getMessage()))
			return userDDO;			
		 if(!adminUserDAO.updateUser(userDDO)) userDDO.setMessage(Constant.COMMON_ERROR);
		 return userDDO;
	}

	public AdminUserDAO getAdminUserDAO() {
		return adminUserDAO;
	}

	public void setAdminUserDAO(AdminUserDAO adminUserDAO) {
		this.adminUserDAO = adminUserDAO;
	}




}
