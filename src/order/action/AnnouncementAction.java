package order.action;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.db.DDO.AnnouncementDDO;
import order.service.AnnouncementService;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.wechat.common.Config;
import com.wechat.sub.JsSdk;
import com.wechat.sub.Sign;

public class AnnouncementAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private AnnouncementService announcementService;

	private String appId;
	private String timestamp;
	private String nonceStr;
	private String signature;

	private String linkUrl;
	private String imgUrl;

	/**
	 * 已经购买的数量
	 */
	private Integer syNum;

	/**
	 * 
	 */
	private String ticket;

	public Integer getSyNum() {
		return syNum;
	}

	public void setSyNum(Integer syNum) {
		this.syNum = syNum;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	private int id;

	private String announcementName;

	private String announcementDesc;

	private Date announcementDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnnouncementName() {
		return announcementName;
	}

	public void setAnnouncementName(String announcementName) {
		this.announcementName = announcementName;
	}

	public String getAnnouncementDesc() {
		return announcementDesc;
	}

	public void setAnnouncementDesc(String announcementDesc) {
		this.announcementDesc = announcementDesc;
	}

	public Date getAnnouncementDate() {
		return announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	public AnnouncementAction() {
		announcementService = (AnnouncementService) MethodUtil
				.getBean(ServletActionContext.getServletContext(),
						"announcementService");
	}

	

	public String del() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		response.setContentType("text/html;charset=UTF-8");
		announcementService.delAnnouncement(id);

		// if (MethodUtil.isNotNull(sceneryDDO.getMessage())) {
		// log.info("删除公告失败," + sceneryDDO);
		// response.getWriter().print(sceneryDDO.getMessage());
		// } else {
		// log.info("删除公告成功," + sceneryDDO);
		response.getWriter().print(Constant.SUCCESS);
		// }

		return null;
	}

	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}

		announcementService.addAnnouncement(storeDDO.getId(), announcementName,
				announcementDesc);

		response.getWriter().print(Constant.SUCCESS);
		return null;
	}

	public String getPicDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			return Constant.PAGE_SCENERYPIC;
		}
		AnnouncementDDO ddo = announcementService.getAnnouncementById(id);
		if (ddo != null)
			request.setAttribute("announcementDDO", ddo);

		return Constant.PAGE_ANNOUNCEMENT_SCENERYPIC;
	}

	

	/**
	 * 修改
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modify() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		announcementService.saveOrUpdate(id, announcementName);
		response.getWriter().print(Constant.SUCCESS);

		return null;
	}

	public String saveOrUpdate() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}

		// 更新产品描述
		AnnouncementDDO announcementDDO = announcementService
				.getAnnouncementById(id);
		announcementDDO.setAnnouncementDesc(announcementDesc);
		announcementService.saveOrUpdate(announcementDDO);

		response.getWriter().print(Constant.SUCCESS);

		return null;
	}
	
	/**
	 * PC管理端公告查找
	 * Ajax返回JSON字符串,Menu页面中Ajax加载时用到
	 * 
	 * @return
	 * @throws Exception
	 */
	public String get() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String ret = announcementService.getAnnouncementByStoreId(announcementName);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(ret);
		return null;
	}
	
	/**
	 * 手机端获得公告详细
	 * @return
	 * @throws Exception
	 */
	public String getDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();

		AnnouncementDDO ddo = announcementService.getAnnouncementById(id);
		syNum = announcementService.getPartnersNums();
		if (ddo != null)
			request.setAttribute("announcementDDO", ddo);

		JsSdk jsSdk = new JsSdk();
		ticket = jsSdk.ticket();

		setAppId(Config.instance().getAppid());

		String jsapi_ticket = ticket;

		// 注意 URL 一定要动态获取，不能 hardcode
		String url = Constant.doMain;
		// request.getRequestURL().toString();
		url = url + request.getRequestURI();
		String queryString = request.getQueryString().toString();
		if (!queryString.isEmpty()) {
			url = url + "?" + queryString;
		}

		Map<String, String> ret = Sign.sign(jsapi_ticket, url);
		for (@SuppressWarnings("rawtypes")
		Map.Entry entry : ret.entrySet()) {
			System.out.println(entry.getKey() + ", " + entry.getValue());
			if (entry.getKey().equals("url")) {
				linkUrl = entry.getValue().toString();
			} else if (entry.getKey().equals("jsapi_ticket")) {
				// jsapi_ticket=entry.getValue().toString();
			} else if (entry.getKey().equals("timestamp")) {
				setTimestamp(entry.getValue().toString());
			} else if (entry.getKey().equals("nonceStr")) {
				setNonceStr(entry.getValue().toString());
			} else if (entry.getKey().equals("signature")) {
				setSignature(entry.getValue().toString());
			}
		}

		return Constant.PAGE_ANNOUNCEMENT_PIC;
	}
}