package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 应用权限
 * @author Roy Huang
 *
 */
public enum AuthType {
	
	MANAGE(1),USE(2);

	private int value;
	
	AuthType(int value){
		this.value=value;
	}
	
	@JsonValue
	public int value(){
		return this.value;
	}
	
	@JsonCreator
	public static AuthType valueOfValue(int value){
		if(value==MANAGE.value())
			return MANAGE;
		else
			return USE;
	}
	
	@Override
	public String toString(){
		return Integer.toString(value());
	}
}
