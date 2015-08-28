package com;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

@SuppressWarnings("deprecation")
public class Constant {

	
	public static HttpParams httpParams;  
	  
		public static ClientConnectionManager connectionManager;  
		public static HttpContext  localContext = null;  
		public static   BasicCookieStore  cookieStore =null;  
		
	    /** 
	     * 最大连接数 
	     */  
	    public final static int MAX_TOTAL_CONNECTIONS = 800;  
	    /** 
	     * 获取连接的最大等待时间 
	     */  
	    public final static int WAIT_TIMEOUT = 60000;  
	    /** 
	     * 每个路由最大连接数 
	     */  
	    public final static int MAX_ROUTE_CONNECTIONS = 800;  
	    /** 
	     * 连接超时时间 
	     */  
	    public final static int CONNECT_TIMEOUT = 10000;  
	    /** 
	     * 读取超时时间 
	     */  
	    public final static int READ_TIMEOUT = 100000;  
}
