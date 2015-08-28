package order.web.util;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;



 

/**

 * http client utils

 * @author gongsheng.wei

 */

@SuppressWarnings("deprecation")
public class HttpUtils {
	
	public static final String POST ="POST";
	public static final String GET ="GET";

  public static HttpClient client =null;
	public static String get(String url) throws Exception{
		try {
			HttpClient  client = createClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK){
				throw new RuntimeException("status error :"+statusCode+" for "+url);
			}
			return  EntityUtils.toString(response.getEntity());
		} catch (Exception e) {
			e.printStackTrace();
 			 throw new RuntimeException(e.getMessage()==null?e.getCause().getMessage():e.getMessage());
		}
	}
	
	public static String get(String url,Map<String,String> paras) throws Exception{
		try {
			HttpClient  client = createClient();
			HttpGet httpget = new HttpGet(concatUrl(url, paras));
			HttpResponse response = client.execute(httpget);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK){
				throw new RuntimeException("status error :"+statusCode+" for "+url);
			}
			return  EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage()==null?e.getCause().getMessage():e.getMessage());
		}
	}
	
	public static String concatUrl(String url,Map<String,String> paras) throws Exception{
		if(paras == null || paras.isEmpty()|| url == null){
			return url;
		}
		StringBuilder re = new StringBuilder();
		re.append(url);
		if(url.indexOf('?') <0){
			re.append('?');
		}
//		boolean noOtherPara = true;
//		if(re.lastIndexOf("?") != re.length()-1){//
//			noOtherPara = false;
//		}
		Set<String> kes = paras.keySet();
		int i=0;
		 for (String key : kes) {
			 if(i>0){
				 re.append('&');
			 }
			 re.append(key).append('=').append(URLEncoder.encode(paras.get(key),"UTF-8"));
			 ++i;
		}
		 return re.toString();
	}
	
	
	private static  List<NameValuePair> createPara(Map<String,String> source){
		if(source == null || source.isEmpty()){
			return null;
		}
		List<NameValuePair> re = new ArrayList<NameValuePair>();
		Set<String> kes = source.keySet();
		 for (String key : kes) {
			 re.add(new BasicNameValuePair(key,source.get(key)));
		}
		return re;
	}
	
	
	private static HttpClient createClient(){
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
		
		HttpParams cp = httpclient.getParams();
		cp.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 5*1000);//连接超时
		cp.setParameter(CoreConnectionPNames.SO_TIMEOUT, 3*60*1000);//数据读取时间3分钟
		return httpclient;
	}
	
	public static String post(String url,Map<String,String> para){
		try {
			HttpClient  client = createClient();
			HttpPost httpPost = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(createPara(para)));
			HttpResponse response = client.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK){
				throw new RuntimeException("status error :"+statusCode+" for "+url);
			}
			return  EntityUtils.toString(response.getEntity());
			
		} catch (Exception e) {
 			 throw new RuntimeException(e.getMessage()==null?e.getCause().getMessage():e.getMessage());
		}
	}
	
	
    @SuppressWarnings("serial")
	public static void main(String[] args) {
    	try {
    		
    		System.out.println(get("http://www.spict.com/portinfo/Normal/CTNInfoPage.aspx",new HashMap<String, String>(){{ put("ctnno", "6217475");}}));

//			System.out.println(get("http://www.yuangong.org/wg1.php",new HashMap<String, String>(){{ put("txt", "1");}}));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}