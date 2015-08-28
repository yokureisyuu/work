package order.action;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.geometry.Positions;
import order.db.DDO.ProductDDO;
import order.db.DDO.SceneryDDO;
import order.service.ProductService;
import order.service.SceneryService;
import order.web.DDO.ProductWebDDO;
import order.web.DDO.SceneryWebDDO;
import order.web.util.Constant;
import order.web.util.ConvertVideo;
import order.web.util.MethodUtil;
import order.web.util.PropertiesUtil;
import order.web.util.QRCodeUtil;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

public class SceneryAction extends BaseAction {

	Log log = LogFactory.getLog(this.getClass());

	private SceneryService sceneryService;
	private ProductService productService;

	private String detail;

	private int sceneryStoreId;

	private String sceneryName;

	private String sceneryHead;

	private String sceneryDesc;

	private int id;

	private File image;

	private String imageFileName;

	private String imageContentType;

	private String index;

	/**
	 * 视频编号
	 */
	private String videoNo;

	/**
	 * 介绍分类
	 */
	private String classNo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSceneryStoreId() {
		return sceneryStoreId;
	}

	public void setSceneryStoreId(int sceneryStoreId) {
		this.sceneryStoreId = sceneryStoreId;
	}

	public String getSceneryName() {
		return sceneryName;
	}

	public void setSceneryName(String sceneryName) {
		this.sceneryName = sceneryName;
	}

	public String getSceneryHead() {
		return sceneryHead;
	}

	public void setSceneryHead(String sceneryHead) {
		this.sceneryHead = sceneryHead;
	}

	public String getSceneryDesc() {
		return sceneryDesc;
	}

	public void setSceneryDesc(String sceneryDesc) {
		this.sceneryDesc = sceneryDesc;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getVideoNo() {
		return videoNo;
	}

	public void setVideoNo(String videoNo) {
		this.videoNo = videoNo;
	}

	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public boolean checkFileType(String fileType) {
		if (Constant.IMAGE_TYPE_GIF.equalsIgnoreCase(fileType)
				|| Constant.IMAGE_TYPE_JPG.equalsIgnoreCase(fileType)
				|| Constant.IMAGE_TYPE_JPEG.equalsIgnoreCase(fileType)
				|| Constant.IMAGE_TYPE_PNG.equalsIgnoreCase(fileType)) {
			return true;
		}
		return false;
	}

	public SceneryAction() {

		sceneryService = (SceneryService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "sceneryService");
		productService = (ProductService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "productService");
	}

	/**
	 * Ajax返回JSON字符串,Menu页面中Ajax加载时用到
	 * PC管理端产品列表
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
		String ret = sceneryService.getScenerysByStoreId(storeDDO.getId(),
				sceneryName);
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(ret);
		return null;
	}

	public String getDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			return Constant.PAGE_SCENERY;
		}
		SceneryDDO ddo = sceneryService.getDetail(id);

		ProductDDO productDDO = productService
				.getProductByStoreIdAndDisplayOrder(id, classNo);
		request.setAttribute("product", productDDO);

		if (ddo != null)
			request.setAttribute("scenery", ddo);

		return Constant.PAGE_SCENERY;
	}

	public String getPicDetail() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			return Constant.PAGE_SCENERYPIC;
		}
		SceneryDDO ddo = sceneryService.get(id);
		if (ddo != null)
			request.setAttribute("scenerypic", ddo);

		return Constant.PAGE_SCENERYPIC;
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
		SceneryDDO sceneryDDO = sceneryService.delScenery(id);
		if (MethodUtil.isNotNull(sceneryDDO.getMessage())) {
			log.info("删除景点失败," + sceneryDDO);
			response.getWriter().print(sceneryDDO.getMessage());
		} else {
			log.info("删除景点成功," + sceneryDDO);
			response.getWriter().print(Constant.SUCCESS);
		}

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
		ProductDDO productDDO = productService
				.getProductByStoreIdAndDisplayOrder(id, classNo);
		productDDO.setProductDesc(detail);
		boolean ret = productService.updateQrCode(productDDO);

		ret = sceneryService.saveOrUpdate(id, detail);
		if (ret)
			response.getWriter().print(Constant.SUCCESS);
		else
			response.getWriter().print(
					PropertiesUtil.getProperties(Constant.COMMON_ERROR));
		return null;
	}

	public String updateVideo() throws Exception {
		boolean flag = false; // 转码成功与否的标记
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);

			return null;
		}
		String filePath = "";
		SceneryDDO qrCodeDDO = sceneryService.getDetail(id);
		String videoPath = qrCodeDDO.getVideo();
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			String videoName = new Date().getTime() + "";
			String video = videoName + fileType;
			String dstFilePath = Constant.path + "video" + "/" + video;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "video" + "/" + videoName;
			if (".mp4".equalsIgnoreCase(fileType)) {// 若上传格式为MP4，则不需转码
				qrCodeDDO.setVideo(filePath + fileType);

				ProductDDO productDDO = productService
						.getProductByStoreIdAndDisplayOrder(id, classNo);
				if ("1".equals(videoNo)) {
					productDDO.setVideo1Name(sceneryName);
					productDDO.setVideo1Desc(sceneryDesc);
					productDDO.setVideo1(filePath + fileType);
				} else if ("2".equals(videoNo)) {
					productDDO.setVideo2Name(sceneryName);
					productDDO.setVideo2Desc(sceneryDesc);
					productDDO.setVideo2(filePath + fileType);
				} else if ("3".equals(videoNo)) {
					productDDO.setVideo3Name(sceneryName);
					productDDO.setVideo3Desc(sceneryDesc);
					productDDO.setVideo3(filePath + fileType);
				}
				productService.updateQrCode(productDDO);

			} else {// 转码
				@SuppressWarnings("deprecation")
				String mencoderPath = ServletActionContext.getRequest()
						.getRealPath("/tools/mencoder.exe");
				String codcFilePath = Constant.path + "video" + "/" + videoName
						+ ".mp4";
				String tempFilePath = Constant.path + "temp" + "/" + videoName
						+ ".avi";
				flag = new ConvertVideo(mencoderPath, tempFilePath).convert(
						dstFilePath, codcFilePath);
				if (flag) {
					qrCodeDDO.setVideo(filePath + ".mp4");
				} else {
					response.getWriter().print(Constant.COMMON_ERROR);
					return null;
				}
			}
		}
		boolean ret = sceneryService.updateQrCode(qrCodeDDO);
		if (!ret) {
			response.getWriter().print(Constant.COMMON_ERROR);
		} else {
			response.getWriter().print(Constant.SUCCESS);
			if (MethodUtil.isNotNull(videoPath)) {// 若是替换以前的，则将保持在本地的以前文件删除
				String targetFile = Constant.path + "video" + "/"
						+ MethodUtil.getFileName(videoPath);
				File file = new File(targetFile);
				if (file.exists())
					file.delete();
			}
		}
		request.setAttribute("scenery", qrCodeDDO);
		return null;
	}

	public String updateVoice() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String filePath = "";
		SceneryDDO qrCodeDDO = sceneryService.getDetail(id);
		String voicePath = qrCodeDDO.getVoice();
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			String voice = new Date().getTime() + fileType;
			String dstFilePath = Constant.path + "voice" + "/" + voice;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "voice" + "/" + voice;

			ProductDDO productDDO = productService
					.getProductByStoreIdAndDisplayOrder(id, classNo);
			if ("1".equals(videoNo)) {
				productDDO.setVoice1Name(sceneryName);
				productDDO.setVoice1Desc(sceneryDesc);
				productDDO.setVoice1(filePath + fileType);
			} else if ("2".equals(videoNo)) {
				productDDO.setVoice2Name(sceneryName);
				productDDO.setVoice2Desc(sceneryDesc);
				productDDO.setVoice2(filePath + fileType);
			} else if ("3".equals(videoNo)) {
				productDDO.setVoice3Name(sceneryName);
				productDDO.setVoice3Desc(sceneryDesc);
				productDDO.setVoice3(filePath + fileType);
			}
			productService.updateQrCode(productDDO);

		}
		qrCodeDDO.setVoice(filePath);
		boolean ret = sceneryService.updateQrCode(qrCodeDDO);
		if (!ret) {
			response.getWriter().print(Constant.COMMON_ERROR);
		} else {
			response.getWriter().print(Constant.SUCCESS);
			if (MethodUtil.isNotNull(voicePath)) {// 若是替换以前的，则将保持在本地的以前文件删除
				String targetFile = Constant.path + "voice" + "/"
						+ MethodUtil.getFileName(voicePath);
				File file = new File(targetFile);
				if (file.exists())
					file.delete();
			}
		}
		request.setAttribute("scenery", qrCodeDDO);
		return null;
	}

	public String generateQrCode() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		SceneryDDO qrCode = sceneryService.getDetail(id);
		String qrCodePic = "";
		String info = "";

		ProductDDO productDDO = new ProductDDO();
		if ("1".equals(index)) {
			qrCodePic = qrCode.getQrCodeVoicePic();
			info = Constant.qrCodeUrl + "scenerygetQrCodeVoice?id=" + id;

			productDDO = productService.getProductByStoreIdAndDisplayOrder(id,
					classNo);

		} else {
			qrCodePic = qrCode.getQrCodeVideoPic();
			info = Constant.qrCodeUrl + "scenerygetQrCodeVideo?id=" + id;
		}
		String picname = qrCode.getSceneryHead();
		String logoPath = Constant.path + "pic" + "/"
				+ MethodUtil.getFileName(picname);
		String qrCodePath = Constant.path + "qrcode";
		String qrCodeName = new Date().getTime() + ".png";
		QRCodeUtil.encode(info, logoPath, qrCodePath, true, qrCodeName);
		String filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR
				+ "/" + "qrcode" + "/" + qrCodeName;
		if ("1".equals(index)) {
			qrCode.setQrCodeVoicePic(filePath);
		} else {
			qrCode.setQrCodeVideoPic(filePath);
		}
		boolean ret = sceneryService.updateQrCode(qrCode);
		if (!ret) {
			response.getWriter().print(Constant.COMMON_ERROR);
		} else {
			response.getWriter().print(Constant.SUCCESS);
			if (MethodUtil.isNotNull(qrCodePic)) {// 若是替换以前的，则将保持在本地的以前文件删除
				String targetFile = Constant.path + "qrcode" + "/"
						+ MethodUtil.getFileName(qrCodePic);
				File file = new File(targetFile);
				if (file.exists())
					file.delete();
			}
		}
		request.setAttribute("scenery", qrCode);
		request.setAttribute("product", productDDO);

		return null;
	}

	public String getQrCodeVoice() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		// if(!isLegalOp(request))return Constant.PAGE_ERROR;
		SceneryDDO ret = sceneryService.get(id);
		if (ret.getVoice() != null) {
			request.setAttribute("sceneryvoice", ret);
		} else {
			request.setAttribute("sceneryvoice", "");
		}
		return Constant.PAGE_VOICE;
	}

	public String getQrCodeVideo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		// if(!isLegalOp(request))return Constant.PAGE_ERROR;
		SceneryDDO ret = sceneryService.get(id);
		if (ret.getVideo() != null) {
			request.setAttribute("sceneryvideo", ret);
		} else {
			request.setAttribute("sceneryvideo", "");
		}
		return Constant.PAGE_VIDEO;
	}

	public String getFrontList() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		if (!isLegalOp(request))
			return Constant.PAGE_ERROR;
		List<SceneryWebDDO> ret = sceneryService.getScenerysByStoreId(storeDDO
				.getId());
		request.setAttribute("scenerys", ret);
		return Constant.PAGE_SCENERY_LIST;
	}

	public String getFrontOneDetail() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		SceneryDDO ret = sceneryService.get(id);
		List<ProductWebDDO> productList = productService
				.getProductsByStoreId(id);

		request.setAttribute("sceneryone", ret);
		request.setAttribute("productList", productList);

		return Constant.PAGE_SCENERY_DETAIL;
	}

	public String getFrontVoice() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		SceneryDDO ret = sceneryService.get(id);
		if (ret.getVoice() != null) {
			response.getWriter().write(ret.getVoice());
		} else {
			response.getWriter().write("");
		}
		return null;
	}

	public String getFrontVideo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setContentType("text/html;charset=utf-8");
		// if(!isLegalOp(request))return Constant.PAGE_ERROR;
		SceneryDDO ret = sceneryService.get(id);
		if (ret.getVideo() != null) {
			request.setAttribute("video", ret.getVideo());
		} else {
			request.setAttribute("video", "");
		}
		return Constant.PAGE_VIDEO2;
	}
	
	/**
	 * 产品图片:添加产品图片
	 * @return
	 * @throws Exception
	 */
	public String add() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String filePath = "";
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			if (!checkFileType(fileType)) {
				response.getWriter().print(
						PropertiesUtil.ppt
								.getProperty(Constant.CHECK_FILE_TYPE));
				return null;
			}
			String headname = new Date().getTime() + fileType;
			String dstFilePath = Constant.path + "pic" + "/" + headname;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			if (imageFile.length() > Constant.max_pic) {
				String toFilePath = Constant.path + "pic" + "/";
				//MethodUtil.cutImage(dstFilePath, toFilePath, 640, 480);
				MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,786,Positions.TOP_LEFT);
			}
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "pic" + "/" + headname;
		}
		SceneryDDO sceneryDDO = sceneryService.addScenery(storeDDO.getId(),
				sceneryName, filePath, sceneryDesc);
		if (MethodUtil.isNotNull(sceneryDDO.getMessage())) {
			log.info("增加景点失败," + sceneryDDO);
			response.getWriter().print(sceneryDDO.getMessage());
		} else {
			response.getWriter().print(Constant.SUCCESS);
		}
		return null;
	}

	
	/**
	 * 产品图片:修改产品图片
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
		String filePath = "";
		SceneryDDO ddo = sceneryService.get(id);
		String oldFilePath = ddo.getSceneryHead();
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			if (!checkFileType(fileType)) {
				response.getWriter().print(
						PropertiesUtil.ppt
								.getProperty(Constant.CHECK_FILE_TYPE));
				return null;
			}
			String headname = new Date().getTime() + fileType;
			String dstFilePath = Constant.path + "pic" + "/" + headname;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			if (imageFile.length() > Constant.max_pic) {
				String toFilePath = Constant.path + "pic" + "/";
				//MethodUtil.cutImage(dstFilePath, toFilePath, 640, 480);
				MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,786,Positions.TOP_RIGHT);
				
			}
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "pic" + "/" + headname;
		} else {
			filePath = sceneryHead;
		}
		SceneryDDO sceneryDDO = sceneryService.modifyScenery(storeDDO.getId(),
				id, sceneryName, filePath, sceneryDesc);
		if (MethodUtil.isNotNull(sceneryDDO.getMessage())) {
			log.info("修改风景失败," + sceneryDDO);
			response.getWriter().print(sceneryDDO.getMessage());
		} else {
			response.getWriter().print(Constant.SUCCESS);
			if (MethodUtil.isNotNull(oldFilePath)
					&& !oldFilePath.equals(filePath)
					&& !Constant.DEFAULT_PIC.equals(MethodUtil
							.getFileName(oldFilePath))) {// 若是替换以前的，则将保持在本地的以前文件删除
				String targetFile = Constant.path + "pic" + "/"
						+ MethodUtil.getFileName(oldFilePath);
				File file = new File(targetFile);
				if (file.exists())
					file.delete();
			}
		}
		return null;
	}
	
	/**
	 * 图片展示：增加图片
	 */
	public String addPic() throws Exception {

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=utf-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String filePath = "";
		String oldFilePath = "";
		SceneryDDO ddo = sceneryService.getDetail(id);
		if ("1".equals(index))
			oldFilePath = ddo.getSceneryPic1();
		else if ("2".equals(index))
			oldFilePath = ddo.getSceneryPic2();
		else if ("3".equals(index))
			oldFilePath = ddo.getSceneryPic3();
		else if ("4".equals(index))
			oldFilePath = ddo.getSceneryPic4();
		else
			oldFilePath = ddo.getSceneryPic5();
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			String headname = new Date().getTime() + fileType;
			String dstFilePath = Constant.path + "pic" + "/" + headname;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			if (imageFile.length() > Constant.max_pic) {
				String toFilePath = Constant.path + "pic" + "/";
				
				
				//MethodUtil.cutImage(dstFilePath, toFilePath, 640, 480);

				if (MethodUtil.isNull(oldFilePath)) {

					// 图片展示：修改图片
					if ("1".equals(index)){
						MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,
								786, Positions.TOP_RIGHT);
					}else{
						MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,
								786, Positions.TOP_LEFT);
					}
					
				} else if (Constant.DEFAULT_PIC.equals(MethodUtil
						.getFileName(oldFilePath))) {
					// 图片展示：修改图片
					MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,
							786, Positions.TOP_RIGHT);

				} else {
					// 图片展示：增加图片
					MethodUtil.ThumbnailsImage(dstFilePath, toFilePath, 840,
							786, Positions.TOP_RIGHT);
				}
				
					
				
			}
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "pic" + "/" + headname;
		}
		boolean ret = sceneryService.saveOrUpdatePic(id, filePath,
				Integer.parseInt(index));
		if (ret) {
			response.getWriter().print(Constant.SUCCESS);
			if (MethodUtil.isNotNull(oldFilePath)
					&& !Constant.DEFAULT_PIC.equals(MethodUtil
							.getFileName(oldFilePath))) {// 若是替换以前的，则将保持在本地的以前文件删除
				String targetFile = Constant.path + "pic" + "/"
						+ MethodUtil.getFileName(oldFilePath);
				File file = new File(targetFile);
				if (file.exists())
					file.delete();
			}
		} else {
			response.getWriter().print(
					PropertiesUtil.getProperties(Constant.COMMON_ERROR));
		}
		return null;
	}
}