package org.developerworld.frameworks.weixin2.qy.callback.dto.rep;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.developerworld.frameworks.weixin2.qy.callback.dto.enums.MsgType;

/**
 * 响应
 * @author Roy Huang
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractRep implements Serializable {
	
	public AbstractRep(){
		this.msgType=MsgType.valueOfRepClass(this.getClass());
	}
	
	@XmlElement(name="ToUserName")
	protected String toUserName;
	@XmlElement(name="FromUserName")
	protected String fromUserName;
	@XmlElement(name="CreateTime")
	protected Long createTime;
	@XmlElement(name="MsgType")
	protected MsgType msgType;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public MsgType getMsgType() {
		return msgType;
	}
//
//	public void setMsgType(MsgType msgType) {
//		this.msgType = msgType;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((fromUserName == null) ? 0 : fromUserName.hashCode());
		result = prime * result + ((msgType == null) ? 0 : msgType.hashCode());
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
		AbstractRep other = (AbstractRep) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (fromUserName == null) {
			if (other.fromUserName != null)
				return false;
		} else if (!fromUserName.equals(other.fromUserName))
			return false;
		if (msgType != other.msgType)
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
		return "AbstractRep [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + "]";
	}

	
}
