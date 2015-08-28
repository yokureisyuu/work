package com;

import order.web.util.ConstantUtil;
import order.web.util.MethodUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;






@SuppressWarnings("deprecation")
public class AcessTokenService {
	
	Log log = LogFactory.getLog(this.getClass());
	
	public  boolean accessToken() {
		StringBuffer sb = new StringBuffer(URLUtil.accessTokenUrl);
		sb.append("?"+ConstantUtil.GRANT_TYPE+"="+SecretUtil.grantType);
		sb.append("&"+ConstantUtil.APPID+"="+SecretUtil.appId);
		sb.append("&"+ConstantUtil.SECRET+"="+SecretUtil.appSecret);
		HttpGet httpget = new HttpGet(sb.toString());
		
		DefaultHttpClient httpclient = new DefaultHttpClient(Constant.connectionManager, Constant.httpParams);
		try {
			log.info("accessToken  request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget,Constant.localContext);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				String ret = EntityUtils.toString(entity, ConstantUtil.ENCODE);
				log.info(": " + ret);
				JSONObject jsonObj = new JSONObject(ret);
				String accesstoken =(String)jsonObj.get(ConstantUtil.ACCESSTOKEN);
				Integer expirein =(Integer)jsonObj.get(ConstantUtil.EXPIRES_IN);
				if(!MethodUtil.isNull(accesstoken)&&expirein>0){
					log.info("获取access token成功,access token:"+accesstoken+",expire in:"+expirein);
					SecretUtil.accessToken=accesstoken;
					SecretUtil.expirein=expirein;
					SecretUtil.accessTokenStatus=true;
					return true;
				}else{
					String errorcode =(String)jsonObj.get(ConstantUtil.ERRCODE);
					String errormsg =(String)jsonObj.get(ConstantUtil.ERRMSG);
					log.info("获取access token失败,errorcode "+errorcode+",errormsg:"+errormsg);
					SecretUtil.accessTokenStatus=false;
					return false;
				}
		 

			}
		} catch ( Exception e) {
			log.info("获取access token失败");
			log.error(e);
			e.printStackTrace();
		} 
		return false;
	}
	public static void main(String[]args){
		 new AcessTokenService().accessToken();
		System.out.println("access 成功:"+SecretUtil.accessToken);
		
	}
}
