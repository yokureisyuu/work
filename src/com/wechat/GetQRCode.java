package com.wechat;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
*
* 项目名称：wechatapi
* 类名称：CreateQRCode
* 类描述：获取二维码工具
* 创建人：Myna Wang
* 创建时间：2014-3-7 下午3:35:29
* @version
*/
public class GetQRCode extends CommonUtil{
	/**
	 * 通过ticket换取二维码
	 *
	 * @param ticket 获取的二维码ticket
	 * @param savePath 保存路径
	 * @return String
	 */
	public static String getQRCode(String ticket,String savePath) {
		String filePath=null;
		String requestUrl=GET_QRCODE_URL.replace("TICKET", urlEncodeUTF8(ticket));
		try {
			URL url=new URL(requestUrl);
			HttpURLConnection conn=(HttpURLConnection)url.openConnection();
			conn.setDoInput(true);
			conn.setRequestMethod("GET");
			if (!savePath.endsWith("/")) {
				savePath+="/";
			}
			// 将ticket作为文件名
			filePath=savePath;
			// 将微信服务器返回的输入流写入文件
			BufferedInputStream bis=new BufferedInputStream(conn.getInputStream());
			FileOutputStream fos=new FileOutputStream(new File(filePath));
			byte[] buf=new byte[8096];
			int size=0;
			while ((size = bis.read(buf)) != -1)
				fos.write(buf, 0, size);
			fos.close();
			bis.close();
			conn.disconnect();
			log.info("根据ticket换取二维码成功，filePath="+filePath);
		} catch (Exception e) {
			filePath=null;
			log.error("根据ticket换取二维码失败:{}",e);
		}
		return filePath;
	}

	public static void main(String[] args) {
		String ticket="gQFY8ToAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL1NremlXRkhsOTFtT1NlTVpSMkNFAAIE_pPRVAMEAAAAAA==";
		
		//String savePath = Constant.qrCodeUrl + "/upload/map/qrcode/" ;

		String savePath="D:/silktravelUploads" + "/upload/map/qrcode/admin.jpg" ;
		// 根据ticket换取二维码
		getQRCode(ticket, savePath);
	}

}
