package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.base.ChatInfoBase;

/**
 * 聊天会话请求
 * 
 * @author Roy Huang
 *
 */
public class UpdateChatInfoReq extends ChatInfoBase {

	private String opUser;
	private List<String> addUserList;
	private List<String> delUserList;

	public String getOpUser() {
		return opUser;
	}

	public void setOpUser(String opUser) {
		this.opUser = opUser;
	}

	public List<String> getAddUserList() {
		return addUserList;
	}

	public void setAddUserList(List<String> addUserList) {
		this.addUserList = addUserList;
	}

	public List<String> getDelUserList() {
		return delUserList;
	}

	public void setDelUserList(List<String> delUserList) {
		this.delUserList = delUserList;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((addUserList == null) ? 0 : addUserList.hashCode());
		result = prime * result + ((delUserList == null) ? 0 : delUserList.hashCode());
		result = prime * result + ((opUser == null) ? 0 : opUser.hashCode());
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
		UpdateChatInfoReq other = (UpdateChatInfoReq) obj;
		if (addUserList == null) {
			if (other.addUserList != null)
				return false;
		} else if (!addUserList.equals(other.addUserList))
			return false;
		if (delUserList == null) {
			if (other.delUserList != null)
				return false;
		} else if (!delUserList.equals(other.delUserList))
			return false;
		if (opUser == null) {
			if (other.opUser != null)
				return false;
		} else if (!opUser.equals(other.opUser))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UpdateChatInfoReq [opUser=" + opUser + ", addUserList=" + addUserList + ", delUserList=" + delUserList
				+ ", charid=" + charid + ", name=" + name + ", owner=" + owner + "]";
	}

}
