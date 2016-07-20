package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户分类
 * 
 * @author Roy Huang
 *
 */
public enum UserType {

	CREATOR(1), INTERNAL_ADMIN(2), EXTERNAL_ADMIN(3), L2L_ADMIN(4), MEMBER(5);

	private int value;

	UserType(int value) {
		this.value = value;
	}

	@JsonValue
	public int value() {
		return value;
	}

	@JsonCreator
	public static UserType valueOfValue(int value) {
		if (value == CREATOR.value())
			return CREATOR;
		else if (value == INTERNAL_ADMIN.value())
			return INTERNAL_ADMIN;
		else if (value == EXTERNAL_ADMIN.value())
			return EXTERNAL_ADMIN;
		else if (value == L2L_ADMIN.value())
			return L2L_ADMIN;
		else
			return MEMBER;
	}

	@Override
	public String toString() {
		return Integer.toString(value());
	}
}
