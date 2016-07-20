package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * text消息请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class TextMessageReq extends AbstractMessageReq{

	@XmlElement(name="Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
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
		TextMessageReq other = (TextMessageReq) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TextMessage [content=" + content + ", msgId=" + msgId + ", toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}
	
}
