/**
 * 上海恺易集成系统技术有限公司信息
 * (c) Copyright 2007-2013 上海恺易
 * @package   com.ce.terrace.common
 * @filename  RandomValidateCode.java
 * @author   bo.hu
 * @CREATE DATE 2014-4-1
 * @MODIFIED BY
 * @DESCRIPTION 
 */
package com.wechat.sub;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import order.web.util.QRCodeUtil;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * @茶树的二维码
 * 二维码中间放logo的地方放数字，0001放到0500
 * 
 * @author bo.hu
 * @version CVN #V1# #2014-4-1#
 */
public class MoiteaQrCodeTrace {

	// 水印
	public String moitea(String moiteaQrcode, String center, String moitea,
			String sn) throws Exception {

		String waterTicket = moitea + "water/" + sn + ".jpg";

		Thumbnails.of(moiteaQrcode).size(600, 1024)

		.watermark(Positions.CENTER, ImageIO.read(new File(center)), 1.0f)
				.outputQuality(0.8f).toFile(waterTicket);

		File file = new File(moiteaQrcode);
		if (file.exists())
			file.delete();

		return waterTicket;
	}

	private String createJpgByFont(String s, int smallWidth, Color bgcolor,
			Color fontcolor, String fontPath, String jpgname) {
		try {
			// 宽度 高度
			BufferedImage bimage = new BufferedImage(s.length() * smallWidth
					/ 2, smallWidth, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bimage.createGraphics();
			g.setColor(bgcolor); // 背景色
			g.fillRect(0, 0, smallWidth * s.length(), smallWidth); // 画一个矩形
			// System.out.println( s.length()+" "+smallWidth);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			// 去除锯齿(当设置的字体过大的时候,会出现锯齿)
			g.setColor(fontcolor); // 字的颜色
			File file = new File(fontPath); // 字体文件
			Font font = Font.createFont(Font.TRUETYPE_FONT, file);
			// 根据字体文件所在位置,创建新的字体对象(此语句在jdk1.5下面才支持)
			g.setFont(font.deriveFont((float) smallWidth-10));
			// font.deriveFont(float f)复制当前 Font 对象并应用新设置字体的大小
			g.drawString(s, 1, smallWidth - 16);
			// 在指定坐标除添加文字
			g.dispose();
			FileOutputStream out = new FileOutputStream(jpgname); // 指定输出文件
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bimage);
			param.setQuality(50f, true);
			encoder.encode(bimage, param); // 存盘
			out.flush();
			out.close();
		} catch (Exception e) {
			System.out.println("createJpgByFont Failed!");
			e.printStackTrace();
		}
		return "Retruning";
	}

	public static void main(String[] args) {

		try {
			int totalQRCode=500;
			for (int i = 1; i <= totalQRCode; i++) {
				String sn = "0000" + i; 
				sn = sn.substring(sn.length() -4, sn.length());
				sn="JM"+sn;

				String qrCodeName = sn + "back.png";
				QRCodeUtil.encode("http://m.moitea.cn/moitea.html?code=" + sn,
						"", "E:/work/kaiyi/m/result", true, qrCodeName);

				String moiteabackSn = "E:/work/kaiyi/m/result/" + qrCodeName;
				String moiteaSn = "E:/work/kaiyi/m/result/" + sn + ".png";
				String moitea = "E:/work/kaiyi/m/";

				MoiteaQrCodeTrace e = new MoiteaQrCodeTrace();
				e.createJpgByFont(sn, 100, Color.BLUE, Color.WHITE,
						"E:/work/kaiyi/m/times.ttf", moiteaSn);
				e.moitea(moiteabackSn, moiteaSn, moitea, sn);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}