package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 素材类型
 * @author Roy Huang
 *
 */
public enum MediaType {
	
	IMAGE("image"),VOICE("voice"),VIDEO("video"),FILE("file"),MPNEWS("mpnews");

	private String value;
	MediaType(String value){
		this.value=value;
	}
	@JsonValue
	public String value(){
		return value;
	}
	@Override
	public String toString(){
		return value;
	}
}
