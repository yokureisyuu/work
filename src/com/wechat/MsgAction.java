package com.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import order.web.util.ConstantUtil;

import com.wechat.main.MessService;


/**
 * 这个类为微信访问此服务器的接口
 * @author Administrator
 *
 */
public class MsgAction extends HttpServlet {

	private static final long serialVersionUID = -4732937385806322206L;

	//验证URL有效性
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
	
		//String param = request.getParameter("p");
		//System.out.println(param);
		String signature = request.getParameter(ConstantUtil.SIGNATURE);
		String timestamp = request.getParameter(ConstantUtil.TIMESTAMP);
		String nonce = request.getParameter(ConstantUtil.NONCE);
		String echostr = request.getParameter(ConstantUtil.ECHOSTR);
		
		System.out.println(signature + ", " + signature);
		System.out.println(timestamp + ", " + timestamp);
		System.out.println(nonce + ", " + nonce);
		
		//String checkRet =new MsgService().checkUrl(signature, timestamp, nonce, echostr);
		//if(!MethodUtil.isNull(checkRet))
		if(echostr!=null)
		response.getWriter().write(echostr);
		//response.setContentType("text/html;charset=UTF-8");
		//response.getWriter().write("今天是个好日子");
	}

	/**
	 * 接受微信转发过来的客户普通消息，事件消息，语音消息
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

	        
		 // 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");    
        
//    	String signature = request.getParameter(ConstantUtil.SIGNATURE);
//		String timestamp = request.getParameter(ConstantUtil.TIMESTAMP);
//		String nonce = request.getParameter(ConstantUtil.NONCE);
//		String echostr = request.getParameter(ConstantUtil.ECHOSTR);
        
        //String str=this.getStream(request);  
        //System.out.println("接收消息为:"+str);
        
        // 调用核心业务类接收消息、处理消息  
        String respMessage = MessService.process(request);            
      
        
        
	   
        System.out.println("回复消息为:"+respMessage);

        response.setContentType("textml;charset=utf-8");

	    response.getWriter().write(respMessage);
	        
	}
	
	public String getStream(HttpServletRequest request) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(request.getInputStream(),ConstantUtil.ENCODE));
        StringBuffer ReString=new StringBuffer();
        String tmp="";
        while(true){
            tmp=br.readLine();
            if(tmp==null)
                break;
            else
                ReString.append(tmp);
        }
        return ReString.toString();
    }
   
}
