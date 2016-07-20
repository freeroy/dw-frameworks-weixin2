package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 成员进入应用的事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class EnterAgentEventReq extends AbstractEventReq {
	
	@XmlElement(name="EventKey")
	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventKey == null) ? 0 : eventKey.hashCode());
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
		EnterAgentEventReq other = (EnterAgentEventReq) obj;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EnterAgentEventReq [eventKey=" + eventKey + ", event=" + event + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", agentID=" + agentID + "]";
	}

}
