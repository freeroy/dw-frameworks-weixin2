package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 地理位置上报
 * @author Roy Huang
 *
 */
public enum ReportLocationFlag {
	
	OFF(0),SESSION_ON(1),ON(2);

	private int value;
	
	ReportLocationFlag(int value){
		this.value=value;
	}
	
	@JsonValue
	public int value(){
		return value;
	}
	
	@JsonCreator
	public static ReportLocationFlag valueOfValue(int value){
		if(value==OFF.value())
			return OFF;
		else if(value==SESSION_ON.value())
			return SESSION_ON;
		else
			return ON;
	}
	
	@Override
	public String toString(){
		return Integer.toString(value());
	}
}
