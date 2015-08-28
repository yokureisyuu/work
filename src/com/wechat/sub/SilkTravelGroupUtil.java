package com.wechat.sub;

import java.util.List;

import com.wechat.CommonUtil;
import com.wechat.GroupUtil;
import com.wechat.WeixinGroup;


/**  
*   
* 项目名称：wechatapi  
* 类名称：GroupUtil  
* 类描述：用户组操作工具  
* 创建人：Myna Wang  
* 创建时间：2014-3-8 下午2:17:48  
* @version       
*/
public class SilkTravelGroupUtil extends CommonUtil{
	
	/**
	 * 移动用户分组
	 * 
	 * @param accessToken 调用接口凭证
	 * @param openId 用户唯一标识符
	 * @param groupId 分组id
	 * @return true|false
	 */
	public static boolean removeMemberGroups(String openId,String groupName) {
		boolean result=false;
		// 获取接口访问凭证
				String accessToken=getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET).getAccesstoken();
				
				// 获取分组列表
				List<WeixinGroup> groupList=GroupUtil.getGroups(accessToken);
				// 循环输出各分组信息
				for(WeixinGroup group:groupList){
					System.err.println(String.format("ID: %d 名称: %s 用户数: %d", group.getId(),
							group.getName(),group.getCount()));
					if (group.getName().equals(groupName)){
						// 移动用户分组
						if (SilkTravelGetWeixinUserInfo.getPersonGroupId(openId)!=group.getId()){
							 result=GroupUtil.removeMemberGroups(accessToken,openId, group.getId());
						}
						break;
					}
				}
		
		return result;
	}

	public static void main(String[] args) {
		// 获取接口访问凭证
		String accessToken=getAccessToken(WEIXIN_APPID, WEIXIN_APPSECRET).getAccesstoken();
		
		// 创建分组
		WeixinGroup groupManager=GroupUtil.createGroup(accessToken, "景区管理员");
		System.err.println(String.format("创建景区管理员组成功: %s id: %d", groupManager.getName(),groupManager.getId()));
		
		// 获取分组列表
		List<WeixinGroup> groupList=GroupUtil.getGroups(accessToken);
		// 循环输出各分组信息
		for(WeixinGroup group:groupList){
			System.err.println(String.format("ID: %d 名称: %s 用户数: %d", group.getId(),
					group.getName(),group.getCount()));
//			if (group.getName().equals("景区管理员")){
//				// 移动用户分组
//				boolean result2=GroupUtil.removeMemberGroups(accessToken, "odIK5uJFzt2cg1zZTEpTVdx8sJVo", group.getId());
//			}
		}

	}
}
