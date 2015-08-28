package com;


import java.util.TimerTask;
import javax.servlet.ServletContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 因为AccessToken生命周期为2小时，这里每隔1小时更新一次AccessToken
 */
public class AccessTokenTimeTask extends TimerTask {

	Log log = LogFactory.getLog(this.getClass());

	@SuppressWarnings("unused")
	private ServletContext context = null;

	private boolean accessTokenStatus;

	public AccessTokenTimeTask(ServletContext context) {
		this.context = context;
	}

	@Override
	public void run() {

		if (!accessTokenStatus) {

			log.info("开始获取Token");
			boolean accessTokenStatus = new AcessTokenService().accessToken();
			if (accessTokenStatus) {
				// context.log("获取Token成功");
				log.info("获取Token成功");
			} else {
				log.info("获取Token失败");
				try {
					Thread.sleep(1000 * 60);// 如果获取失败,睡一分钟，继续获取Token
				} catch (InterruptedException e) {
					//  Auto-generated catch block
					log.error(e.getMessage());
					e.printStackTrace();
				}

			}
		}

	}

	/**
	 * 执行任务
	 */
	public void executeTask() {
		System.out.println("任务1");
		System.out.println("任务2");
	}
}