package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 应用类型
 * 
 * @author Roy Huang
 *
 */
public enum AgentType {

	MSG(1), PAGE(2);

	private int value;

	AgentType(int value) {
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

	@JsonCreator
	public static AgentType valueOfValue(int value) {
		if (value == MSG.value())
			return MSG;
		else
			return PAGE;
	}
}
