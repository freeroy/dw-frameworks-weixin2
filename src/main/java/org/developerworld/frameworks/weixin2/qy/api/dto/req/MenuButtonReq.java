package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.MenuButtonBase;

/**
 * 菜单对象
 * 
 * @author Roy Huang
 *
 */
public class MenuButtonReq extends MenuButtonBase<MenuButtonReq> {

	@Override
	public String toString() {
		return "MenuButtonReq [name=" + name + ", type=" + type + ", key=" + key + ", url=" + url + ", subButton="
				+ subButton + "]";
	}

}
