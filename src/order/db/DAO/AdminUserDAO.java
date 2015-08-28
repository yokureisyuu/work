package order.db.DAO;

import java.util.ArrayList;

import order.db.DDO.AdminUserDDO;
import order.web.util.Constant;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class AdminUserDAO {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public AdminUserDDO register(AdminUserDDO user) {

		// String hql= "from UserDDO u where u.phone=?";
		Integer insertNum = (Integer) this.getHibernateTemplate().save(user);
		if (insertNum > 0) {

			log.info("注册成功一个管理员用户,用户名：" + user.getName() + ",手机：" + user.getPhone());
			return user;
		}
		return user;

	}


	public AdminUserDDO login(AdminUserDDO user) {

		String hql = "from AdminUserDDO u where u.name=? and u.password=?";
		@SuppressWarnings("rawtypes")
		ArrayList userDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] { user.getName(), user.getPassword() });
		if (userDDOList != null && userDDOList.size() > 0)
			return (AdminUserDDO) userDDOList.get(0);
		user.setMessage(Constant.USER_LOGIN_ERROR);
		return user;
	}


	public boolean updateUser(AdminUserDDO userDDO){
		try {
			hibernateTemplate.update(userDDO);
			return true;
		} catch (Exception e) {
			log.error(e);
			return false;
		}		
	}
	
	public AdminUserDDO isNameExit(AdminUserDDO userDDO){
		String hql = "from AdminUserDDO u where u.name=? and id<>?";
		@SuppressWarnings("rawtypes")
		ArrayList userDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {userDDO.getName(),userDDO.getId()});
		if (userDDOList != null && userDDOList.size() > 0){
			userDDO.setMessage(Constant.USER_NAME_EXIT_ERROR);
		}else {
			userDDO.setMessage(""); 
		}
		
		return userDDO;
	}
	public AdminUserDDO isPhoneExit(AdminUserDDO userDDO){
		String hql = "from AdminUserDDO u where u.phone=? and id<>?";
		@SuppressWarnings("rawtypes")
		ArrayList userDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Object[] {userDDO.getPhone(),userDDO.getId()});
		if (userDDOList != null && userDDOList.size() > 0){
			userDDO.setMessage(Constant.USER_PHONE_EXIT_ERROR);
		}else{
			userDDO.setMessage(""); 
		}
		return userDDO;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}

