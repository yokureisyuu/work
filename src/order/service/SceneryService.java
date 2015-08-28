package order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import order.db.DAO.SceneryDao;
import order.db.DDO.SceneryDDO;
import order.web.DDO.SceneryWebDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;

public class SceneryService {

	private SceneryDao sceneryDao;
	
	public SceneryDao getSceneryDao() {
		return sceneryDao;
	}

	public void setSceneryDao(SceneryDao sceneryDao) {
		this.sceneryDao = sceneryDao;
	}

	/**
	 * 返回JSON格式
	 * 
	 * @return
	 */
	public String getScenerysByStoreId(int storeId, String sceneryName) {
		List<SceneryDDO> ret;
		ret = sceneryDao.getScenerysByStoreId(storeId, sceneryName);
		if (ret == null || ret.size() == 0)
			return "{\"total\":0,\"rows\":[]}";

		List<SceneryWebDDO> sceneryWebDDOList = new ArrayList<SceneryWebDDO>();
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		for (int i = 0; i < ret.size(); i++) {
			SceneryDDO sceneryDDO = (SceneryDDO) ret.get(i);
			SceneryWebDDO webDDO = new SceneryWebDDO();
			webDDO.setId(sceneryDDO.getId());
			webDDO.setSceneryDesc(sceneryDDO.getSceneryDesc());
			if (MethodUtil.isNull(sceneryDDO.getSceneryHead())) {
				webDDO.setSceneryHead(filePath + Constant.DEFAULT_PIC);
			} else {
				webDDO.setSceneryHead(sceneryDDO.getSceneryHead());
			}
			webDDO.setSceneryName(sceneryDDO.getSceneryName());
			webDDO.setUpdateDate(MethodUtil.formatDateTime(sceneryDDO.getUpdateDate()));

			sceneryWebDDOList.add(webDDO);
		}
		JSONArray jsonArray = JSONArray.fromObject(sceneryWebDDOList);
		String sceneryStr = jsonArray.toString();
		sceneryStr = "{\"total\":" + sceneryWebDDOList.size() + ",\"rows\":" + sceneryStr + "}";
		return sceneryStr;

	}
	
	public List<SceneryWebDDO> getScenerysByStoreId(int storeId) {
		List<SceneryDDO> ret;
		ret = sceneryDao.getScenerysByStoreId(storeId,null);
		if (ret == null || ret.size() == 0)
			return null;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<SceneryWebDDO> sceneryWebDDOList = new ArrayList();
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		for (int i = 0; i < ret.size(); i++) {
			SceneryDDO sceneryDDO = (SceneryDDO) ret.get(i);
			SceneryWebDDO webDDO = new SceneryWebDDO();
			webDDO.setId(sceneryDDO.getId());
			webDDO.setSceneryDesc(sceneryDDO.getSceneryDesc());
			if (MethodUtil.isNull(sceneryDDO.getSceneryHead())) {
				webDDO.setSceneryHead(filePath+Constant.DEFAULT_PIC);
			} else {
				webDDO.setSceneryHead(sceneryDDO.getSceneryHead());
			}
			webDDO.setSceneryName(sceneryDDO.getSceneryName());
			webDDO.setUpdateDate(MethodUtil.formatDateTime(sceneryDDO.getUpdateDate()));

			sceneryWebDDOList.add(webDDO);
		}
		
		return sceneryWebDDOList;

	}
	
	public SceneryWebDDO getScenerysById(int Id) {
		SceneryDDO ret;
		ret = sceneryDao.get(Id);
		if (ret == null)
			return null;

		
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		
			SceneryDDO sceneryDDO = ret;
			SceneryWebDDO webDDO = new SceneryWebDDO();
			webDDO.setId(sceneryDDO.getId());
			webDDO.setSceneryDesc(sceneryDDO.getSceneryDesc());
			if (MethodUtil.isNull(sceneryDDO.getSceneryHead())) {
				webDDO.setSceneryHead(filePath+Constant.DEFAULT_PIC);
			} else {
				webDDO.setSceneryHead(sceneryDDO.getSceneryHead());
			}
			webDDO.setSceneryName(sceneryDDO.getSceneryName());
			webDDO.setUpdateDate(MethodUtil.formatDateTime(sceneryDDO.getUpdateDate()));

			
		
		return webDDO;

	}

	public SceneryDDO delScenery(int id) {
		SceneryDDO sceneryDDO = new SceneryDDO();
		sceneryDDO.setId(id);
		boolean ret = sceneryDao.delScenery(sceneryDDO);
		if (!ret)
			sceneryDDO.setMessage(Constant.COMMON_ERROR);
		return sceneryDDO;
	}

	public SceneryDDO addScenery(int sceneryStoreId, String sceneryName, String sceneryHead, String sceneryDesc) {
		SceneryDDO sceneryDDO = new SceneryDDO();
		sceneryDDO.setStoreId(sceneryStoreId);
		sceneryDDO.setSceneryName(sceneryName);
		sceneryDDO.setSceneryHead(sceneryHead);
		sceneryDDO.setSceneryDesc(sceneryDesc);
		sceneryDDO.setUpdateDate(new Date());
		sceneryDDO = sceneryDao.isSceneryExit(sceneryDDO);
		if (MethodUtil.isNotNull(sceneryDDO.getMessage()))
			return sceneryDDO;
		sceneryDDO = sceneryDao.insertScenery(sceneryDDO);
		if (sceneryDDO.getId() == 0)
			sceneryDDO.setMessage(Constant.COMMON_ERROR);
		return sceneryDDO;
	}

	public SceneryDDO modifyScenery(int sceneryStoreId, int id, String sceneryName, String sceneryHead, String sceneryDesc) {

		SceneryDDO sceneryDDO = sceneryDao.get(id);
		if (sceneryDDO == null)
			sceneryDDO = new SceneryDDO();
		sceneryDDO.setStoreId(sceneryStoreId);
		sceneryDDO.setSceneryName(sceneryName);
		sceneryDDO.setSceneryHead(sceneryHead);
		sceneryDDO.setSceneryDesc(sceneryDesc);
		sceneryDDO.setUpdateDate(new Date());
		sceneryDDO.setId(id);
		sceneryDDO = sceneryDao.isSceneryExit2(sceneryDDO);
		if (MethodUtil.isNotNull(sceneryDDO.getMessage()))
			return sceneryDDO;
		boolean ret = sceneryDao.updateScenery(sceneryDDO);
		if (!ret)
			sceneryDDO.setMessage(Constant.COMMON_ERROR);
		return sceneryDDO;
	}

	public SceneryDDO getDetail(int id) {
		SceneryDDO ddo = sceneryDao.get(id);
		if (ddo == null) {
			ddo = new SceneryDDO();
		}
		ddo.setId(id);
		return ddo;
	}

	public SceneryDDO get(int id) {
		SceneryDDO ddo = sceneryDao.get(id);
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		if(ddo!=null){
			if (MethodUtil.isNull(ddo.getSceneryPic1()) && MethodUtil.isNull(ddo.getSceneryPic2()) && MethodUtil.isNull(ddo.getSceneryPic3()) && MethodUtil.isNull(ddo.getSceneryPic4())
					&& MethodUtil.isNull(ddo.getSceneryPic5())) {
				ddo.setSceneryPic1(ddo.getSceneryHead());
			}
		}else{
			ddo = new SceneryDDO();
			ddo.setId(id);
			if (MethodUtil.isNull(ddo.getSceneryPic1()) && MethodUtil.isNull(ddo.getSceneryPic2()) && MethodUtil.isNull(ddo.getSceneryPic3()) && MethodUtil.isNull(ddo.getSceneryPic4())
					&& MethodUtil.isNull(ddo.getSceneryPic5())) {
				ddo.setSceneryPic1(filePath+Constant.DEFAULT_PIC);
			}
		}
		return ddo;
	}

	public boolean updateQrCode(SceneryDDO qrCode) {
		qrCode.setUpdateDate(new Date());
		return sceneryDao.updateScenery(qrCode);
	}

	public boolean saveOrUpdate(int id, String detail) {
		SceneryDDO sceneryDDO = sceneryDao.get(id);
		if (sceneryDDO == null)
			sceneryDDO = new SceneryDDO();

		sceneryDDO.setSceneryDetail(detail);

		if (id != 0)
			sceneryDDO.setId(id);
		sceneryDDO.setUpdateDate(new Date());
		return sceneryDao.saveOrUpdate(sceneryDDO);
	}
	
	public boolean saveOrUpdatePic(int id, String pic, int index) {
		SceneryDDO sceneryDDO = sceneryDao.get(id);
		if (sceneryDDO == null)
			sceneryDDO = new SceneryDDO();
		if (index == 1)
			sceneryDDO.setSceneryPic1(pic);
		else if (index == 2)
			sceneryDDO.setSceneryPic2(pic);
		else if (index == 3)
			sceneryDDO.setSceneryPic3(pic);
		else if (index == 4)
			sceneryDDO.setSceneryPic4(pic);
		else
			sceneryDDO.setSceneryPic5(pic);
		sceneryDDO.setId(id);

		sceneryDDO.setUpdateDate(new Date());
		return sceneryDao.saveOrUpdate(sceneryDDO);
	}
}
