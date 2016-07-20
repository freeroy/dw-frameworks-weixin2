package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.commons.api.dto.BooleanToIntegerSerializer;
import org.developerworld.frameworks.weixin2.qy.api.dto.base.UserBase;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * 用户对象
 * @author Roy Huang
 *
 */
public class UserReq extends UserBase{
	

	@JsonSerialize(using=BooleanToIntegerSerializer.class)
	private Boolean enable;
	private String avatarMediaid;

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public void setAvatarMediaid(String avatarMediaid) {
		this.avatarMediaid = avatarMediaid;
	}

	public Boolean getEnable() {
		return enable;
	}

	public String getAvatarMediaid() {
		return avatarMediaid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((avatarMediaid == null) ? 0 : avatarMediaid.hashCode());
		result = prime * result + ((enable == null) ? 0 : enable.hashCode());
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
		UserReq other = (UserReq) obj;
		if (avatarMediaid == null) {
			if (other.avatarMediaid != null)
				return false;
		} else if (!avatarMediaid.equals(other.avatarMediaid))
			return false;
		if (enable == null) {
			if (other.enable != null)
				return false;
		} else if (!enable.equals(other.enable))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserReq [enable=" + enable + ", avatarMediaid=" + avatarMediaid + ", userid=" + userid + ", name="
				+ name + ", department=" + department + ", position=" + position + ", mobile=" + mobile + ", gender="
				+ gender + ", email=" + email + ", weixinid=" + weixinid + ", extattr=" + extattr + "]";
	}

	
}
