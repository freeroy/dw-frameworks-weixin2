package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 音频消息请求
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class VoiceMessageReq extends AbstractMessageReq {


	@XmlElement(name="MediaId")
	private String mediaId;

	@XmlElement(name="Format")
	private String format;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
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
		VoiceMessageReq other = (VoiceMessageReq) obj;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VoiceMessageReq [mediaId=" + mediaId + ", format=" + format + ", msgId=" + msgId + ", toUserName="
				+ toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", agentID=" + agentID + "]";
	}

}
