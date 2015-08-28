package order.action;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.service.StoreService;
import order.web.util.Constant;
import order.web.util.MethodUtil;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

public class StoreAction extends BaseAction {

	private StoreService storeService;

	private int picindex;
	private String name;
	private String comment;
	private String welcome;
	private File image;
	private String imageFileName;
	private String imageContentType;

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPicindex() {
		return picindex;
	}

	public void setPicindex(int picindex) {
		this.picindex = picindex;
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public StoreAction() {

		storeService = (StoreService) MethodUtil.getBean(
				ServletActionContext.getServletContext(), "storeService");

	}

	public String uploadPic() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}
		String filePath = "";
		String oldFilePath = "";
		if (picindex == 1)
			oldFilePath = storeDDO.getStoreSwitchImage1();
		else if (picindex == 2)
			oldFilePath = storeDDO.getStoreSwitchImage2();
		else if (picindex == 3)
			oldFilePath = storeDDO.getStoreSwitchImage3();
		else if (picindex == 4)
			oldFilePath = storeDDO.getStoreSwitchImage4();
		else
			oldFilePath = storeDDO.getStoreSwitchImage5();
		if (image != null) {
			String fileType = MethodUtil.getExtention(imageFileName);
			String headname = new Date().getTime() + fileType;
			String dstFilePath = Constant.path + "pic" + "/" + headname;
			File imageFile = new File(dstFilePath);
			FileUtils.copyFile(image, imageFile);
			if (imageFile.length() > Constant.max_pic) {
				String toFilePath = Constant.path + "pic" + "/";
				MethodUtil.cutImage(dstFilePath, toFilePath, 640, 480);
			}
			filePath = Constant.QIANQIOU_PATH + "/" + Constant.UPLOAD_DIR + "/"
					+ "pic" + "/" + headname;
		}
		if (picindex == 1)
			storeDDO.setStoreSwitchImage1(filePath);
		else if (picindex == 2)
			storeDDO.setStoreSwitchImage2(filePath);
		else if (picindex == 3)
			storeDDO.setStoreSwitchImage3(filePath);
		else if (picindex == 4)
			storeDDO.setStoreSwitchImage4(filePath);
		else
			storeDDO.setStoreSwitchImage5(filePath);
		boolean ret = storeService.updateStore(storeDDO);
		if (!ret) {
			response.getWriter().print(Constant.COMMON_ERROR);
		} else {
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
		}
		request.getSession().setAttribute("store", storeDDO);
		return null;
	}

	public String submit() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		String error = isLegalUserOpForAjax(request);
		if (error != null) {
			response.getWriter().print(error);
			return null;
		}

		storeDDO.setStoreComm(comment);
		storeDDO.setStoreDiscountInfo(welcome);
		storeDDO.setStoreName(name);
		boolean ret = storeService.updateStore(storeDDO);
		if (ret)
			response.getWriter().print(Constant.SUCCESS);
		else
			response.getWriter().print(Constant.COMMON_ERROR);
		return null;

	}

}