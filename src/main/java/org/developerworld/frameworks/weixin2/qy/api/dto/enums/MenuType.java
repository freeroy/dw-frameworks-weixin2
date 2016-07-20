package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 菜单类型
 * @author Roy Huang
 *
 */
public enum MenuType {

	CLICK("click"), VIEW("view"), SCANCODE_PUSH("scancode_push"), SCANCODE_WAITMSG("scancode_waitmsg"), PIC_SYSPHOTO(
			"pic_sysphoto"), PIC_PHOTO_OR_ALBUM("pic_photo_or_album"), PIC_WEIXIN("pic_weixin"), LOCATION_SELECT(
					"location_select");

	private String value;

	MenuType(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
