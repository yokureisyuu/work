package order.web.listen;



import java.io.InputStreamReader;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import order.web.util.Constant;
import order.web.util.PropertiesUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WebListener implements ServletContextListener{

	Log log = LogFactory.getLog(this.getClass());
	
    @SuppressWarnings("unused")
	private java.util.Timer timer = null;
    
	public void contextDestroyed(ServletContextEvent event) {
		//  Auto-generated method stub
		
	}

	public void contextInitialized(ServletContextEvent event) {
		
          Constant.path =  event.getServletContext().getInitParameter("path");
          Constant.qrCodeUrl =  event.getServletContext().getInitParameter("qrCodeUrl");
          Constant.doMain =  event.getServletContext().getInitParameter("doMain");
       
		PropertiesUtil.ppt = new Properties();
		@SuppressWarnings("unused")
		String path;
		try {
			path = (getClass().getClassLoader().getResource("").toURI()).getPath();
			//FileInputStream fis = new FileInputStream(path + "message.properties");
			//PropertiesUtil.ppt.load(fis);
			PropertiesUtil.ppt.load(new InputStreamReader(WebListener.class.getClassLoader().getResourceAsStream("message.properties"), "UTF-8")); 
		} catch (Exception e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
	
		
	}

}

