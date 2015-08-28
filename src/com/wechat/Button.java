package com.wechat;

/**
 * 一级菜单数组(父按钮)，个数应为1~3个
 * 
 * @author tiangai
 * @since 2014-06-30 Am 11:40
 * @version 1.0
 */
public class Button {
	/**
	 * 菜单标题，不超过16个字节，子菜单不超过40个字节
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
