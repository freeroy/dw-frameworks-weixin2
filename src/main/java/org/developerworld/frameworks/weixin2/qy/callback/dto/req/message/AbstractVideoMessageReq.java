package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;

/**
 * 视频消息请求
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractVideoMessageReq extends AbstractMessageReq {

	@XmlElement(name="MediaId")
	protected String mediaId;

	@XmlElement(name="ThumbMediaId")
	protected String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((thumbMediaId == null) ? 0 : thumbMediaId.hashCode());
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
		AbstractVideoMessageReq other = (AbstractVideoMessageReq) obj;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (thumbMediaId == null) {
			if (other.thumbMediaId != null)
				return false;
		} else if (!thumbMediaId.equals(other.thumbMediaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractVideoMessageReq [mediaId=" + mediaId + ", thumbMediaId=" + thumbMediaId + ", msgId=" + msgId
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
