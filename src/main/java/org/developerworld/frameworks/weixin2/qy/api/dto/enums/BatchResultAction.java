package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 批处理操作类型
 * 
 * @author Roy Huang
 *
 */
public enum BatchResultAction {

	UPDATE(1), CREATE(2), MOVE(4), UPDATE_ORDER(8);

	private int value;

	BatchResultAction(int value) {
		this.value = value;
	}

	@JsonValue
	public int value() {
		return value;
	}

	@JsonCreator
	public static BatchResultAction valueOfValue(int value) {
		if (UPDATE.value() == value)
			return UPDATE;
		else if (CREATE.value() == value)
			return CREATE;
		else if (MOVE.value() == value)
			return MOVE;
		else
			return UPDATE_ORDER;
	}

	@Override
	public String toString() {
		return value + "";
	}
}
