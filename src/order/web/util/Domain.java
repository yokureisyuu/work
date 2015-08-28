package order.web.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



 

/**

 * http client utils

 * @author gongsheng.wei

 */

public class Domain {
	
	

	public String getTopDomainWithoutSubdomain(String url)throws MalformedURLException {

		String host = new URL(url).getHost().toLowerCase();// 此处获取值转换为小写
		//Pattern pattern = Pattern.compile("[^\\.]+(\\.com\\.cn|\\.net\\.cn|\\.org\\.cn|\\.gov\\.cn|\\.com|\\.net|\\.cn|\\.org|\\.cc|\\.me|\\.tel|\\.mobi|\\.asia|\\.biz|\\.info|\\.name|\\.tv|\\.hk|\\.公司|\\.中国|\\.网络)");
		  //Pattern pattern =Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE);  
		//  Pattern pattern =Pattern.compile("[^//]*?\\.(com|cn|net|org|biz|info|cc|tv)", Pattern.CASE_INSENSITIVE); 
		  Pattern pattern = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+"); 
		Matcher matcher = pattern.matcher(host);
		while (matcher.find()) {
			return matcher.group();
		}
		return null;
	}
	
	public  String joinUrl(String curl,String file){
		  URL url = null;
		  String q = "";
		  try {
		   url = new   URL(new   URL(curl),file);
		   q = url.toExternalForm();
		  } catch (MalformedURLException e) {   
		 
		  }
		  url = null;
		  if(q.indexOf("#")!=-1)q = q.replaceAll("^(.+?)#.*?$", "$1");
		  return q;
		 }
	
    public static void main(String[] args) {
    	try {
    		
    		Domain d=new Domain();
    		String url = "http://m.yzyw.56jyt.com/yzyw/msg.do";
    		String topDoamin = d.getTopDomainWithoutSubdomain(url);
    		System.out.println(topDoamin);
    		
    		System.out.println(d.joinUrl("http://m.yzyw.56jyt.com/yzyw/msg.do","/yzyw"));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}