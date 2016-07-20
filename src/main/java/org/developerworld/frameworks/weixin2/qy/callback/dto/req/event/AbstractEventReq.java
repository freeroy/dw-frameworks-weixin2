package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlElement;

import org.developerworld.frameworks.weixin2.qy.callback.dto.enums.EventType;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;

/**
 * 事件请求
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractEventReq extends AbstractReq {

	public AbstractEventReq() {
		this.event = EventType.valueOf(this.getClass());
	}

	@XmlElement(name="Event")
	protected EventType event;

//	public void setEvent(EventType event) {
//		this.event = event;
//	}

	public EventType getEvent() {
		return event;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((event == null) ? 0 : event.hashCode());
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
		AbstractEventReq other = (AbstractEventReq) obj;
		if (event != other.event)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractEventReq [event=" + event + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
