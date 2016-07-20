package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;

import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;

/**
 * 消息请求
 * 
 * @author Roy Huang
 *
 */
public  class AbstractMessageReq extends AbstractReq {

	@XmlElement(name="MsgId")
	protected Long msgId;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((msgId == null) ? 0 : msgId.hashCode());
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
		AbstractMessageReq other = (AbstractMessageReq) obj;
		if (msgId == null) {
			if (other.msgId != null)
				return false;
		} else if (!msgId.equals(other.msgId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractMessageReq [msgId=" + msgId + ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
