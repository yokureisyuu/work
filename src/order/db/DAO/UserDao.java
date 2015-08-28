/* 
 * Copyright 2008 1fb.net Financial Services.
 *  
 * This document may not be reproduced, distributed or used 
 * in any manner whatsoever without the expressed written 
 * permission of 1st Financial Bank USA.
 */
package order.db.DAO;

import java.sql.SQLException;
import java.util.List;

import order.db.DDO.UserDDO2;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class UserDao {

	Log log = LogFactory.getLog(this.getClass());

	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}






	@SuppressWarnings("unchecked")
	public List<UserDDO2> getAllUser(int storeid,String username,String phone,final int page,final int row ) {

		StringBuffer sb = new StringBuffer();
		sb.append("where storeId="+storeid);
		if(MethodUtil.isNotNull(username)){
			sb.append(" and name='"+username+"'");
		}
		if(MethodUtil.isNotNull(phone)){
			sb.append(" and phone='"+phone+"'");
		}
		final String hql = "from UserDDO2 u  "+sb.toString();
		
		@SuppressWarnings("rawtypes")
		List list1 = getHibernateTemplate().executeFind(
				new  HibernateCallback() {
						public Object doInHibernate(Session session) throws HibernateException, SQLException {
							  List list2 = MethodUtil.getList(session,hql,(page-1)*row,row);
					            return list2;
						}
				          });
				          return list1;
	}
	/**
	 * 查询用户是否有未支付的订单，如有，则不允许再次下单
	 * @param id
	 * @return
	 
	public UserDDO isUserUnpayed(UserDDO userDDO){
		String hql = "from UserDDO u where u.id=?";
		ArrayList userDDOList = (ArrayList) this.getHibernateTemplate().find(hql, new Integer[] {userDDO.getId()});
		if (userDDOList != null && userDDOList.size() > 0){
			userDDO=(UserDDO) userDDOList.get(0);
			if(userDDO.getStatus()==Constant.ORDER_STATUS_UNPAYED)userDDO.setMessage(Constant.USER_UNPAYED_ERROR); 
			
		}else {
			userDDO.setMessage(Constant.USER_NO_EXIT); 
		}
		
		return userDDO;
	}*/



	public int getTotalSize(int storeId,String username,String phone){
		StringBuffer sb = new StringBuffer();
		sb.append("where storeId="+storeId);
		if(MethodUtil.isNotNull(username)){
			sb.append(" and name='"+username+"'");
		}
		if(MethodUtil.isNotNull(phone)){
			sb.append(" and phone='"+phone+"'");
		}
		final String hql = "select count(id) from UserDDO2 "+sb.toString();
	
		
		Long count = (Long)getHibernateTemplate().find(hql).listIterator().next();
		  return count.intValue();
	}
	
	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

}
