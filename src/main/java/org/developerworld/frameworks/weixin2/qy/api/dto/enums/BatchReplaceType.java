package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 批处理类型
 * 
 * @author Roy Huang
 *
 */
public enum BatchReplaceType {

	SYNC_USER("sync_user"), REPLACE_USER("replace_user"), REPLACE_PARTY("replace_party");

	private String value;

	@JsonValue
	public String value() {
		return value;
	}

	@JsonCreator
	public static BatchReplaceType valueOfValue(String value) {
		if (SYNC_USER.value().equals(value))
			return SYNC_USER;
		else if (REPLACE_USER.value().equals(value))
			return REPLACE_USER;
		else
			return REPLACE_PARTY;
	}

	BatchReplaceType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
