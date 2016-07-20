package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.ChatInfoBase;

/**
 * 聊天会话请求
 * 
 * @author Roy Huang
 *
 */
public class ChatInfoReq extends ChatInfoBase {
	private List<String> userlist;

	public List<String> getUserlist() {
		return userlist;
	}

	public void setUserlist(List<String> userlist) {
		this.userlist = userlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((userlist == null) ? 0 : userlist.hashCode());
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
		ChatInfoReq other = (ChatInfoReq) obj;
		if (userlist == null) {
			if (other.userlist != null)
				return false;
		} else if (!userlist.equals(other.userlist))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChatInfoReq [userlist=" + userlist + ", charid=" + charid + ", name=" + name + ", owner=" + owner + "]";
	}

}
