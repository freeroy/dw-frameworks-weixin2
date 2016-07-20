package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import org.apache.commons.lang.StringUtils;
import org.developerworld.frameworks.weixin2.qy.api.dto.base.UserBase;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.UserStatus;

public class UserRep extends UserBase {

	private String avatar;
	private UserStatus status;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	/**
	 * 获取小图
	 * @return
	 */
	public String getAvatarS(){
		if(StringUtils.isNotBlank(avatar) && avatar.endsWith("/0"))
			return avatar.substring(0, avatar.lastIndexOf("/0"))+"/64";
		return null;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRep other = (UserRep) obj;
		if (avatar == null) {
			if (other.avatar != null)
				return false;
		} else if (!avatar.equals(other.avatar))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [avatar=" + avatar + ", status=" + status + ", userid=" + userid + ", name=" + name
				+ ", department=" + department + ", position=" + position + ", mobile=" + mobile + ", gender=" + gender
				+ ", email=" + email + ", weixinid=" + weixinid + ", extattr=" + extattr + "]";
	}

}
