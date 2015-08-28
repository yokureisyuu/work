package order.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HttpFilter implements Filter {
	private FilterConfig filterConfig;

	private String enconfig;

	public void destroy() {
		//  Auto-generated method stub
		this.filterConfig = null;
		this.enconfig = null;
		System.out.println("过滤器销毁成功!");
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		//  Auto-generated method stub
		if (this.enconfig != null) {
//			HttpServletResponse rp = (HttpServletResponse)response;
			request.setCharacterEncoding(this.enconfig);
			response.setContentType(this.enconfig);
			response.setCharacterEncoding(this.enconfig);
			response.setContentType("text/html;charset="+this.enconfig);
			//rp.setHeader("Pragma","No-cache"); 
			//rp.setHeader("Cache-Control","no-cache"); 
			//rp.setDateHeader("Expires", 0);  
			// response.setHeader("Charset", "utf8");
			//System.out.println("过滤器处理中:" + this.enconfig);
		}
		// 处理权交给下一个过滤器;
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		//  Auto-generated method stub
		this.filterConfig = arg0;

		// 获得,web.xml文件中配置languageconfig 的值,我在web.xml文件中配置了languageconfig
		// 的值为:utf-8;
		this.enconfig = this.filterConfig.getInitParameter("languageconfig");
		System.out.println("过滤器初始化成功!");
	}
}
