package order.web.util;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class MethodUtil {

	private static WebApplicationContext webApplicationContext;

	public static boolean isNull(String str) {

		if (str == null)
			return true;
		if ("".equals(str.trim()))
			return true;
		return false;
	}

	public static boolean isNotNull(String str) {

		if (str != null && !"".equals(str.trim()))
			return true;
		return false;
	}

	public static Object getBean(ServletContext servletContext, String bean) {

		webApplicationContext = WebApplicationContextUtils
				.getRequiredWebApplicationContext(servletContext);
		return webApplicationContext.getBean(bean);

	}

	public static String formatDateTime(Date date) {
		if (date == null)
			return "";
		try {
			java.text.DateFormat format = new java.text.SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String s = null;
			s = format.format(date);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String formatDateTimeForSerial(Date date) {
		if (date == null)
			return "";
		try {
			java.text.DateFormat format = new java.text.SimpleDateFormat(
					"yyyyMMddHHmmss");
			String s = null;
			s = format.format(date);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static List<?> getList(Session session, String hql, int offset,
			int length) {
		Query q = session.createQuery(hql);
		q.setFirstResult(offset);
		q.setMaxResults(length);
		List<?> list = q.list();
		return list;
	}

	/**
	 * Caused by: javax.imageio.IIOException: Unsupported Image Type产生原因：
	 * ps或其他软件处理过的图片保存为jpg格式时，默认的模式是CMYK模式（这是给印刷机用的）。 在图像-->模式中改为RGB模式才是显示器用的。
	 * 解决办法：方法一：通知用户修改图片格式为RGB。
	 * 
	 * @param srcPath
	 * @param toPath
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 *             Unsupported Image Type
	 */
	public static boolean ThumbnailsImage(String srcPath, String toPath,
			int width, int height,Positions positions) {
		try {
				    
			File srcFile = new File(srcPath);
			File destFile = new File(toPath, srcFile.getName());
			
			JpegReader jpegReader=new JpegReader();
			ImageIO.write(jpegReader.readImage(srcFile), "jpg", destFile);

			
			
			//图片右下400*400的区域
			Thumbnails.of(destFile)
					.sourceRegion(positions,width,height)
					.size(width, height)
	                .keepAspectRatio(false) 
			        .toFile(destFile.getPath());
			
			
			
			return true;

		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
			
			return false;
		}

	}

	/**
	 * Caused by: javax.imageio.IIOException: Unsupported Image Type产生原因：
	 * ps或其他软件处理过的图片保存为jpg格式时，默认的模式是CMYK模式（这是给印刷机用的）。 在图像-->模式中改为RGB模式才是显示器用的。
	 * 解决办法：方法一：通知用户修改图片格式为RGB。
	 * 
	 * @param srcPath
	 * @param toPath
	 * @param width
	 * @param height
	 * @return
	 * @throws IOException
	 *             Unsupported Image Type
	 */
	public static boolean cutImage(String srcPath, String toPath, int width,
			int height) {
		try {

			// File srcFile = new File(srcPath);
			// File destFile = new File(toPath, srcFile.getName());
			// Thumbnails.of(srcPath)
			// .size(width, height)
			// .outputFormat("png")
			// .toFile(destFile.getPath());

			File srcFile = new File(srcPath);

			BufferedImage image = ImageIO.read(srcFile);
			int srcWidth = image.getWidth(null);
			int srcHeight = image.getHeight(null);
			int newWidth = 0, newHeight = 0;
			// int x = 0, y = 0;
			double scale_w = (double) width / srcWidth;
			double scale_h = (double) height / srcHeight;
			// System.out.println("scale_w=" + scale_w + ",scale_h=" + scale_h);
			// 按原比例缩放图片
			if (scale_w > 1)
				scale_w = 1;
			if (scale_h > 1)
				scale_h = 1;

			if (scale_w < scale_h) {
				newHeight = (int) (srcHeight * scale_w);
				newWidth = width;
			} else {
				newHeight = height;
				newWidth = (int) (srcWidth * scale_h);
			}
			BufferedImage newImage = new BufferedImage(newWidth, newHeight,
					BufferedImage.TYPE_INT_RGB);
			newImage.getGraphics().drawImage(
					image.getScaledInstance(newWidth, newHeight,
							Image.SCALE_SMOOTH), 0, 0, null);
			// 保存缩放后的图片
			String fileSufix = srcFile.getName().substring(
					srcFile.getName().lastIndexOf(".") + 1);
			File destFile = new File(toPath, srcFile.getName());
			ImageIO.write(newImage, fileSufix, destFile);
			return true;

		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
			if (e.getMessage() == "Unsupported Image Type") {
				System.err
						.println("ps或其他软件处理过的图片保存为jpg格式时，默认的模式是CMYK模式（这是给印刷机用的）,请修改图片格式为RGB！");
			}
			return false;
		}

	}

	/**
	 * 截取文件的后缀
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getExtention(String fileName) throws Exception {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos);
	}

	/**
	 * 截取文件的名称
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String getFileName(String filePath) throws Exception {
		int pos = filePath.lastIndexOf("/");
		return filePath.substring(pos + 1);
	}

}
