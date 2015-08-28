package com.wechat.sub;

import java.io.File;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import order.web.util.Constant;

public class SilkTravelQrcodeTicket {

	
	
//	//指定大小进行缩放
//	private void test1(String path) throws Exception {
//		//size(宽度, 高度)
//		
//        /*  
//         * 若图片横比200小，高比300小，不变  
//         * 若图片横比200小，高比300大，高缩小到300，图片比例不变  
//         * 若图片横比200大，高比300小，横缩小到200，图片比例不变  
//         * 若图片横比200大，高比300大，图片按比例缩小，横为200或高为300  
//         */ 
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//		        .size(200, 300)
//		        .toFile("c:/a380_200x300.jpg");
//		
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//		        .size(2560, 2048) 
//		        .toFile("c:/a380_2560x2048.jpg");
//	}
//	
//	//按照比例进行缩放
//	private void test2(String path) throws Exception {
//		//scale(比例)
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//		        .scale(0.25f)
//		        .toFile("c:/a380_25%.jpg");
//		
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//		        .scale(1.10f)
//		        .toFile("c:/a380_110%.jpg");
//	}
//	
//	//不按照比例，指定大小进行缩放
//	private void test3(String path) throws Exception {
//		//keepAspectRatio(false) 默认是按照比例缩放的
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//		        .size(200, 200) 
//                .keepAspectRatio(false) 
//		        .toFile("c:/a380_200x200.jpg");
//	}
//	
//	//旋转
//	private void test4(String path) throws Exception {
//		//rotate(角度),正数：顺时针 负数：逆时针
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//		        .rotate(90) 
//		        .toFile("c:/a380_rotate+90.jpg"); 
//
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//		        .rotate(-90) 
//		        .toFile("c:/a380_rotate-90.jpg"); 
//	}
//	
//	//水印
//	private void test5(String path) throws Exception {
//		//watermark(位置，水印图，透明度)
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//		        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path+"images/watermark.png")), 0.5f) 
//                .outputQuality(0.8f) 
//		        .toFile("c:/a380_watermark_bottom_right.jpg");
//		
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//		        .watermark(Positions.CENTER, ImageIO.read(new File(path+"images/watermark.png")), 0.5f) 
//		        .outputQuality(0.8f) 
//		        .toFile("c:/a380_watermark_center.jpg");
//	}
//	
//	//裁剪
//	private void test6(String path) throws Exception {
//		//sourceRegion()
//		
//		//图片中心400*400的区域
//		Thumbnails.of(path+"images/a380_1280x1024.jpg")
//				.sourceRegion(Positions.CENTER, 400,400)
//				.size(200, 200)
//                .keepAspectRatio(false) 
//		        .toFile("c:/a380_region_center.jpg");
//		
//		//图片右下400*400的区域
//		Thumbnails.of(path+"images/a380_1280x1024.jpg")
//				.sourceRegion(Positions.BOTTOM_RIGHT, 400,400)
//				.size(200, 200)
//                .keepAspectRatio(false) 
//		        .toFile("c:/a380_region_bootom_right.jpg");
//		
//		//指定坐标
//		Thumbnails.of(path+"images/a380_1280x1024.jpg")
//				.sourceRegion(600, 500, 400, 400)
//				.size(200, 200)
//		        .keepAspectRatio(false) 
//		        .toFile("c:/a380_region_coord.jpg");
//	}
//	
//	//转化图像格式
//	private void test7(String path) throws Exception {
//		//outputFormat(图像格式)
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//                .outputFormat("png") 
//		        .toFile("c:/a380_1280x1024.png"); 
//		
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//		        .outputFormat("gif") 
//		        .toFile("c:/a380_1280x1024.gif"); 
//	}
//	
//	//输出到OutputStream
//	private void test8(String path) throws Exception {
//		//toOutputStream(流对象)
//		OutputStream os = new FileOutputStream("c:/a380_1280x1024_OutputStream.png");
//		Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//                .toOutputStream(os);
//	}
//	
//	//输出到BufferedImage
//	private void test9(String path) throws Exception {
//		//asBufferedImage() 返回BufferedImage
//		BufferedImage thumbnail = Thumbnails.of(path+"images/a380_1280x1024.jpg") 
//				.size(1280, 1024)
//				.asBufferedImage();
//		ImageIO.write(thumbnail, "jpg", new File("c:/a380_1280x1024_BufferedImage.jpg")); 
//	}
	
	
	//水印
		public String ticket(String ticketQrcode,String ticket) throws Exception {
			String waterTicket=ticket;
			//watermark(位置，水印图，透明度)
//			Thumbnails.of(path+"images/ticketbackground.jpg") 
//					.size(1280, 1024)
//			        .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(path+"images/qrcode_for_silktravel.jpg")), 1.0f) 
//	                .outputQuality(0.8f) 
//			        .toFile("c:/a380_watermark_bottom_right.jpg");
			
			String filePath = Constant.path+ "map/qrcode/" ;
			
			Thumbnails.of(filePath+"images/ticketbackground.jpg") 
				.size(600, 1024)
	
			        .watermark(Positions.CENTER, ImageIO.read(new File(ticketQrcode)), 1.0f) 
			        .outputQuality(0.8f) 
			        .toFile(waterTicket);
			
			File file = new File(ticketQrcode);
			if (file.exists())
				file.delete();
			
			
			return waterTicket;
		}
	
	public static void main(String[] args) {
		SilkTravelQrcodeTicket e = new SilkTravelQrcodeTicket();
		// D:/silktravelUploads/upload/map/qrcode
		//String filePath = Constant.qrCodeUrl + "/upload/map/qrcode/" ;
		String filePath ="D:/silktravelUploads" + "/upload/map/qrcode/" ;

		try {
			e.ticket(filePath,"");
//			e.test1(filePath);
//			e.test2(filePath);
//			e.test3(filePath);
//			e.test4(filePath);
//			e.test5(filePath);
//			e.test6(filePath);
//			e.test7(filePath);
//			e.test8(filePath);
//			e.test9(filePath);
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
