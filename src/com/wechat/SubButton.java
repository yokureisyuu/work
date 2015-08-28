package com.wechat;

/**
 * 普通按钮(子按钮)
 * 
 * @author tiangai
 * @since 2014-06-30 Am 11:42
 * @version 1.0
 */
public class SubButton extends Button {
	/**
	 * 菜单的响应动作类型，目前有click、view两种类型
	 */
	private String type;
	/**
	 * 菜单KEY值，用于消息接口推送，不超过128字节
	 */
	private String key;
	/**
	 * 网页链接，用户点击菜单可打开链接，不超过256字节
	 */
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
