package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KFListType {
	INTERNAL("internal"), EXTERNAL("external");
	
	private String value;
	KFListType(String value){
		this.value=value;
	}
	@JsonValue
	public String value(){
		return value;
	}
	@Override
	public String toString(){
		return this.value;
	}
}
