package order.web.util;

import java.util.Properties;

public class PropertiesUtil {

	public static Properties ppt;
	
	public static String getProperties(String key){
		return ppt.getProperty(key);
	}
	
}
