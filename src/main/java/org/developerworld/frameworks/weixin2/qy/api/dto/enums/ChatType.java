package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 会话类型
 * @author Roy Huang
 *
 */
public enum ChatType {

	SINGLE("single"), GROUP("group");

	private String value;

	ChatType(String value) {
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
