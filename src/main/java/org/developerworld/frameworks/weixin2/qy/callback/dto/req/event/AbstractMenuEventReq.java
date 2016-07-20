package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlElement;

/**
 * 菜单点击事件请求
 * @author Roy Huang
 *
 */
public abstract class AbstractMenuEventReq extends AbstractEventReq{

	@XmlElement(name="EventKey")
	protected String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eventKey == null) ? 0 : eventKey.hashCode());
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
		AbstractMenuEventReq other = (AbstractMenuEventReq) obj;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractMenuEventReq [eventKey=" + eventKey + ", event=" + event + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", agentID=" + agentID + "]";
	}
	
	
}
