package org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 图片响应消息
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class ImageMessageRep extends AbstractMessageRep {

	@XmlTransient
	private String mediaId;
	
	@XmlElementWrapper(name="Image")
	@XmlElement(name="MediaId")
	private String[] getImage(){
		return new String[]{mediaId};
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		ImageMessageRep other = (ImageMessageRep) obj;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ImageMessageRep [mediaId=" + mediaId + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType + "]";
	}
	
	
}
