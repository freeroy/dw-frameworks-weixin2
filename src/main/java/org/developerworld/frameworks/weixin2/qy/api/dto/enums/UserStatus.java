package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 关注状态
 * @author Roy Huang
 *
 */
public enum UserStatus {
	
	SUBSCRIPE(1),DISABLED(2),UNSUBSCRIPE(4);

	private int value;

	UserStatus(int value) {
		this.value = value;
	}

	@JsonValue
	public int value() {
		return value;
	}

	@Override
	public String toString() {
		return Integer.toString(value);
	}
	
	/**
	 * 根据值返回枚举
	 * @param value
	 * @return
	 */
	@JsonCreator
	public static UserStatus valueOfValue(int value) {
		if(value==SUBSCRIPE.value())
			return SUBSCRIPE;
		else if(value==DISABLED.value())
			return DISABLED;
		else
			return UNSUBSCRIPE;
	}
}
