package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.MenuButtonBase;

public class MenuButtonRep extends MenuButtonBase<MenuButtonRep>{

	@Override
	public String toString() {
		return "MenuButtonRep [name=" + name + ", type=" + type + ", key=" + key + ", url=" + url + ", subButton="
				+ subButton + "]";
	}

}
