package order.action;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import order.db.DDO.AdminUserDDO;
import order.db.DDO.StoreDDO;
import order.web.util.Constant;
import order.web.util.PropertiesUtil;

public class BaseAction {

	protected StoreDDO storeDDO ;
	protected AdminUserDDO userDDO ;
	/**
	 * 判断用户是否登录，Action请求时一般要判断这
	 * @param request
	 * @return
	 */
	public boolean isUserLogin(HttpServletRequest request){
		userDDO = (AdminUserDDO) request.getSession().getAttribute("user");
		if(userDDO==null) return false;
		else return true;
	}
	/**
	 * 判断是否是对当前商店的合法操作　
	 * @param request
	 * @return
	 */
	public boolean isLegalOp(HttpServletRequest request){
		 storeDDO = (StoreDDO)request.getSession().getAttribute("store");
		if(storeDDO==null){
			request.setAttribute("errorinfo", PropertiesUtil.getProperties(Constant.USER_ILLEGAL_OP));
			return false;
		}
		
		else return true;
	}

	public String isLegalUserOpForAjax(HttpServletRequest request){
		if (!isLegalOp(request)) {
			return Constant.USER_ILLEGAL_OP;
		}
		if (!isUserLogin(request)) {
			return Constant.USER_ILLEGAL_OP;
		}
		if(userDDO.getUserStoreId()!=storeDDO.getId()) return Constant.USER_ILLEGAL_OP;
		return null;
	}
	public String isLegalUserOpForPage(HttpServletRequest request){
		if (!isLegalOp(request)) {
			return Constant.PAGE_LOGIN;
		}
		if (!isUserLogin(request)) {
			return Constant.PAGE_LOGIN;
		}
		if(userDDO.getUserStoreId()!=storeDDO.getId())	return Constant.PAGE_LOGIN;
		return null;
	}
	/**
	 * 设置本次点餐类型，是外卖，还是室内点餐;1，室内点餐，２，叫外卖
	 *
	 */
	public void setOrderType(HttpServletRequest request,int orderType){
		request.getSession().setAttribute("orderType",orderType);
	}
	public int getOrderType(HttpServletRequest request){
		Object orderType =request.getSession().getAttribute("orderType");
		if(orderType==null)return Constant.ORDER_TYPE_ROOM;
		else return (Integer)orderType;
	}

	public void setOrder(HttpServletRequest request,@SuppressWarnings("rawtypes") List orderDDOList,String totalPrice,String totalDisPrice,int totalNum){
		request.getSession().setAttribute("temporder", orderDDOList);
		request.getSession().setAttribute("totalPrice", totalPrice);
		request.getSession().setAttribute("totalDisPrice", totalDisPrice);
		request.getSession().setAttribute("totalNum", totalNum);
	}
	public void removeOrderSession(HttpServletRequest request){
		request.getSession().removeAttribute("temporder");
		request.getSession().removeAttribute("totalPrice");
		request.getSession().removeAttribute("totalDisPrice");
		request.getSession().removeAttribute("totalNum");
		
	}
	/**
	 * 用户退出登录清空所以Session
	 * @param request
	 */
	public void clearAllSession(HttpServletRequest request){
		removeOrderSession(request);
		request.getSession().removeAttribute("user");
	}
	public void clearStoreSession(HttpServletRequest request){
		removeOrderSession(request);
		request.getSession().removeAttribute("store");
	}
}
