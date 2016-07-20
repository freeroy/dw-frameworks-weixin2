package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 用户免打扰设定
 * @author Roy Huang
 *
 */
public enum UserMuteStatus {
	
	CLOSE(0),OPEN(1);

	private int value;
	
	@JsonValue
	public int value(){
		return value;
	}
	
	UserMuteStatus(int value){
		this.value=value;
	}
	@Override
	public String toString(){
		return value+"";
	}
}
