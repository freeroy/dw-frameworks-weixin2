package org.developerworld.frameworks.weixin2.qy.callback.dto;

/**
 * 加密消息消息
 * 
 * @author Roy Huang
 *
 */
public class EncryptMessage {

	private String toUserName;
	private Integer agentID;
	private String encrypy;

	public EncryptMessage(String toUserName, Integer agentID, String encrypy) {
		this.toUserName = toUserName;
		this.agentID = agentID;
		this.encrypy = encrypy;
	}

	public String getToUserName() {
		return toUserName;
	}

	public Integer getAgentID() {
		return agentID;
	}

	public String getEncrypy() {
		return encrypy;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public void setAgentID(Integer agentID) {
		this.agentID = agentID;
	}

	public void setEncrypy(String encrypy) {
		this.encrypy = encrypy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((agentID == null) ? 0 : agentID.hashCode());
		result = prime * result + ((encrypy == null) ? 0 : encrypy.hashCode());
		result = prime * result + ((toUserName == null) ? 0 : toUserName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EncryptMessage other = (EncryptMessage) obj;
		if (agentID == null) {
			if (other.agentID != null)
				return false;
		} else if (!agentID.equals(other.agentID))
			return false;
		if (encrypy == null) {
			if (other.encrypy != null)
				return false;
		} else if (!encrypy.equals(other.encrypy))
			return false;
		if (toUserName == null) {
			if (other.toUserName != null)
				return false;
		} else if (!toUserName.equals(other.toUserName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EncryptMessage [toUserName=" + toUserName + ", agentID=" + agentID + ", encrypy=" + encrypy + "]";
	}

}
