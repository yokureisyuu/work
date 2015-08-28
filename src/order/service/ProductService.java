package order.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import order.db.DAO.ProductDao;
import order.db.DDO.ProductDDO;
import order.web.DDO.ProductWebDDO;
import order.web.util.Constant;
import order.web.util.MethodUtil;

public class ProductService {

	private ProductDao productDao;
	
	public ProductDao getProductDao() {
		return productDao;
	}

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	/**
	 * 返回JSON格式
	 * 
	 * @return
	 */
	public String getProductsByStoreId(int storeId, String productName) {
		List<ProductDDO> ret;
		ret = productDao.getProductsByStoreId(storeId);
		if (ret == null || ret.size() == 0)
			return "{\"total\":0,\"rows\":[]}";

		List<ProductWebDDO> productWebDDOList = new ArrayList<ProductWebDDO>();
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		for (int i = 0; i < ret.size(); i++) {
			ProductDDO productDDO = (ProductDDO) ret.get(i);
			ProductWebDDO webDDO = new ProductWebDDO();
			webDDO.setId(productDDO.getId());
			webDDO.setProductDesc(productDDO.getProductDesc());
			if (MethodUtil.isNull(productDDO.getVideo1())) {
				webDDO.setVideo1(filePath + Constant.DEFAULT_PIC);
			} else {
				webDDO.setVideo1(productDDO.getVideo1());
			}
			webDDO.setVideo1Name(productDDO.getVideo1Name());
			webDDO.setUpdateDate(MethodUtil.formatDateTime(productDDO.getUpdateDate()));

			productWebDDOList.add(webDDO);
		}
		JSONArray jsonArray = JSONArray.fromObject(productWebDDOList);
		String productStr = jsonArray.toString();
		productStr = "{\"total\":" + productWebDDOList.size() + ",\"rows\":" + productStr + "}";
		return productStr;

	}
	
	public List<ProductWebDDO> getProductsByStoreId(int storeId) {
		List<ProductDDO> ret;
		ret = productDao.getProductsByStoreId(storeId);
		if (ret == null || ret.size() == 0)
			return null;

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<ProductWebDDO> productWebDDOList = new ArrayList();
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		for (int i = 0; i < ret.size(); i++) {
			ProductDDO productDDO = (ProductDDO) ret.get(i);
			ProductWebDDO webDDO = new ProductWebDDO();
			webDDO.setId(productDDO.getId());
			if (productDDO.getDisplayOrder().equals("1")){
				webDDO.setDisplayOrder("产地特点");
			}else if (productDDO.getDisplayOrder().equals("2")){
				webDDO.setDisplayOrder("产品特点");
			}else if (productDDO.getDisplayOrder().equals("3")){
				webDDO.setDisplayOrder("营养价值");
			}else if (productDDO.getDisplayOrder().equals("4")){
				webDDO.setDisplayOrder("食用方法");
			}else if (productDDO.getDisplayOrder().equals("5")){
				webDDO.setDisplayOrder("画册");
			}
			
			webDDO.setVideo1Name(productDDO.getVideo1Name());
			webDDO.setVideo1Desc(productDDO.getVideo1Desc());
			if (MethodUtil.isNull(productDDO.getVideo1())) {
				webDDO.setVideo1(filePath+Constant.DEFAULT_PIC);
			} else {
				webDDO.setVideo1(productDDO.getVideo1());
			}
			
			webDDO.setVideo2Name(productDDO.getVideo2Name());
			webDDO.setVideo2Desc(productDDO.getVideo2Desc());
			if (MethodUtil.isNull(productDDO.getVideo2())) {
				webDDO.setVideo2(filePath+Constant.DEFAULT_PIC);
			} else {
				webDDO.setVideo2(productDDO.getVideo2());
			}
			
			webDDO.setVideo3Name(productDDO.getVideo3Name());
			webDDO.setVideo3Desc(productDDO.getVideo3Desc());
			if (MethodUtil.isNull(productDDO.getVideo3())) {
				webDDO.setVideo3(filePath+Constant.DEFAULT_PIC);
			} else {
				webDDO.setVideo3(productDDO.getVideo3());
			}
			
			
			webDDO.setProductDesc(productDDO.getProductDesc());
			
			webDDO.setUpdateDate(MethodUtil.formatDateTime(productDDO.getUpdateDate()));

			productWebDDOList.add(webDDO);
		}
		
		return productWebDDOList;

	}

	public ProductDDO delProduct(int id) {
		ProductDDO productDDO = new ProductDDO();
		productDDO.setId(id);
		boolean ret = productDao.delProduct(productDDO);
		if (!ret)
			productDDO.setMessage(Constant.COMMON_ERROR);
		return productDDO;
	}

	public ProductDDO addProduct(int productStoreId, String video1Name, String video1Desc, String productDesc) {
		ProductDDO productDDO = new ProductDDO();
		productDDO.setStoreId(productStoreId);
		productDDO.setVideo1Name(video1Name);
		productDDO.setVideo1Desc(video1Desc);
		productDDO.setProductDesc(productDesc);
		productDDO.setUpdateDate(new Date());
		productDDO = productDao.isProductExit(productDDO);
		if (MethodUtil.isNotNull(productDDO.getMessage()))
			return productDDO;
		productDDO = productDao.insertProduct(productDDO);
		if (productDDO.getId() == 0)
			productDDO.setMessage(Constant.COMMON_ERROR);
		return productDDO;
	}

	public ProductDDO modifyProduct(int productStoreId, int id, String productName, String productHead, String productDesc) {

		ProductDDO productDDO = productDao.get(id);
		if (productDDO == null)
			productDDO = new ProductDDO();
		productDDO.setStoreId(productStoreId);
		productDDO.setVideo1Name(productName);
		productDDO.setVideo2Name(productHead);
		productDDO.setProductDesc(productDesc);
		productDDO.setUpdateDate(new Date());
		productDDO.setId(id);
		productDDO = productDao.isProductExit2(productDDO);
		if (MethodUtil.isNotNull(productDDO.getMessage()))
			return productDDO;
		boolean ret = productDao.updateProduct(productDDO);
		if (!ret)
			productDDO.setMessage(Constant.COMMON_ERROR);
		return productDDO;
	}

	public ProductDDO getDetail(int id) {
		ProductDDO ddo = productDao.get(id);
		if (ddo == null) {
			ddo = new ProductDDO();
		}
		ddo.setId(id);
		return ddo;
	}
	
	public ProductDDO getProductByStoreIdAndDisplayOrder(int storeId, String displayOrder) {
		ProductDDO ddo = productDao.getProductByStoreIdAndDisplayOrder(storeId,  displayOrder);
		if (ddo == null) {
			ddo = new ProductDDO();
		}
		//ddo.setId(id);
		return ddo;
	}

	public ProductDDO get(int id) {
		ProductDDO ddo = productDao.get(id);
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/" + "pic" + "/";
		if(ddo!=null){
			if (MethodUtil.isNull(ddo.getVideo1Name()) && MethodUtil.isNull(ddo.getVideo2Name()) && MethodUtil.isNull(ddo.getVideo3Name()) && MethodUtil.isNull(ddo.getProductDesc())
					&& MethodUtil.isNull(ddo.getVideo1Desc())) {
				ddo.setVideo1Desc(ddo.getVideo1Desc());
			}
		}else{
			ddo = new ProductDDO();
			ddo.setId(id);
			if (MethodUtil.isNull(ddo.getVideo1Name()) && MethodUtil.isNull(ddo.getVideo2Name()) && MethodUtil.isNull(ddo.getVideo3Name()) && MethodUtil.isNull(ddo.getProductDesc())
					&& MethodUtil.isNull(ddo.getVideo1Desc())) {
				ddo.setVideo1Desc(filePath+Constant.DEFAULT_PIC);
			}
		}
		return ddo;
	}

	public boolean updateQrCode(ProductDDO qrCode) {
		qrCode.setUpdateDate(new Date());
		return productDao.updateProduct(qrCode);
	}

	public boolean saveOrUpdate(int id, String detail) {
		ProductDDO productDDO = productDao.get(id);
		if (productDDO == null)
			productDDO = new ProductDDO();

		productDDO.setProductDesc(detail);

		if (id != 0)
			productDDO.setId(id);
		productDDO.setUpdateDate(new Date());
		return productDao.saveOrUpdate(productDDO);
	}
	
	public boolean saveOrUpdatePic(int id, String pic, int index) {
		ProductDDO productDDO = productDao.get(id);
		if (productDDO == null)
			productDDO = new ProductDDO();
		if (index == 1)
			productDDO.setVideo1Name(pic);
		else if (index == 2)
			productDDO.setVideo2Name(pic);
		else if (index == 3)
			productDDO.setVideo3Name(pic);
		else if (index == 4)
			productDDO.setVoice1Name(pic);
		else
			productDDO.setVoice2Name(pic);
		productDDO.setId(id);

		productDDO.setUpdateDate(new Date());
		return productDao.saveOrUpdate(productDDO);
	}
}
