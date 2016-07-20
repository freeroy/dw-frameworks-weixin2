package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户性别
 * 
 * @author Roy Huang
 *
 */
public enum UserGender {

	UNKNOWN(0), MAN(1), LADY(2);

	private int value;

	UserGender(int value) {
		this.value = value;
	}

	@JsonValue
	public int value() {
		return value;
	}

	/**
	 * 根据值获取枚举
	 * @param value
	 * @return
	 */
	@JsonCreator
	public static UserGender valueOfValue(int value) {
		if (value == UNKNOWN.value())
			return UserGender.UNKNOWN;
		else if (value == MAN.value())
			return UserGender.MAN;
		else
			return UserGender.LADY;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
}
