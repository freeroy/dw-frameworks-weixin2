package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * image消息请求
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class ImageMessageReq extends AbstractMessageReq {
	
	@XmlElement(name="PicUrl")
	private String picUrl;
	@XmlElement(name="MediaId")
	private String mediaId;

	public String getPicUrl() {
		return picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((picUrl == null) ? 0 : picUrl.hashCode());
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
		ImageMessageReq other = (ImageMessageReq) obj;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (picUrl == null) {
			if (other.picUrl != null)
				return false;
		} else if (!picUrl.equals(other.picUrl))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageMessage [picUrl=" + picUrl + ", mediaId=" + mediaId + ", msgId=" + msgId + ", toUserName="
				+ toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", agentID=" + agentID + "]";
	}

}
