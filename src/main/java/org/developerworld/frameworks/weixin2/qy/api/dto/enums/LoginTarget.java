package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 登录企业号官网目标位置
 * 
 * @author Roy Huang
 *
 */
public enum LoginTarget {

	AGENT_SETTING("agent_setting"), SEND_MSG("send_msg"), CONTACT("contact");

	private String value;

	LoginTarget(String value) {
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
