/**
 * 
 */
package com.wechat.sub;

import java.util.TimerTask;

import org.apache.log4j.Logger;


import com.wechat.token.AccessToken;
import com.wechat.token.server.AccessTokenServer;
import com.wechat.token.server.CustomerServer;

/**
 * access token 定时器
 * @author ChengNing
 * @date   2015年1月8日
 */
public class AccessTokenTimer extends TimerTask{
	
	private static Logger logger = Logger.getLogger(AccessTokenTimer.class);
	
	//accessToken有效期7200秒,提前200秒请求新的token
	public static final long PERIOD = 7000 * 1000;
	public static final long DELAY = 0; //此任务的延迟时间为0，即立即执行

	@Override
	public void run() {
		logger.info("accessToken 定时任务启动，获取新的accessToken");
		//得到新的access token
		AccessToken accessToken = new AccessToken();
		//获取成功之后持久化accessToken
		if(accessToken.request()){
			AccessTokenServer accessTokenServer = new AccessTokenServer();
			CustomerServer customerServer = (CustomerServer)accessTokenServer.customerServer();
			customerServer.save(accessToken);
		}
	}

}
