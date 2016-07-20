package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

/**
 * 登录用户类型
 * 
 * @author Roy Huang
 *
 */
public enum LoginUserType {

	MEMBER("member"), ADMIN("admin"), ALL("all");

	private String value;

	LoginUserType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}
}
