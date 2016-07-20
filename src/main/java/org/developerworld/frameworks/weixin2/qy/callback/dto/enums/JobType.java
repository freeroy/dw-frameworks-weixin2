package org.developerworld.frameworks.weixin2.qy.callback.dto.enums;

public enum JobType {
	SYNC_USER("sync_user"),REPLACE_USER("replace_user"),INVITE_USER("invite_user"),REPLACE_PARTY("replace_party");
	
	
	private String value;
	JobType(String value){
		this.value=value;
	}
	public String value(){
		return value;
	}
	@Override
	public String toString(){
		return value;
	}
}
