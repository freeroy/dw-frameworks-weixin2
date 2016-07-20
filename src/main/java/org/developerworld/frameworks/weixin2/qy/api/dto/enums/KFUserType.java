package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KFUserType {

	KF("kf"), USERID("userid"), OPENID("openid");

	private String value;
	
	@JsonValue
	public String value(){
		return value;
	}

	KFUserType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}
}
