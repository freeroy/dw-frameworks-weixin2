package org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * video消息响应
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.NONE)
public class VideoMessageRep extends AbstractMessageRep {

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Video {
		@XmlElement(name = "MediaId")
		public String mediaId;
		@XmlElement(name = "Title")
		public String title;
		@XmlElement(name = "Description")
		public String description;
	}

	@XmlElement(name = "Video")
	private Video getVideo() {
		Video rst=new Video();
		rst.mediaId=this.mediaId;
		rst.title=this.title;
		rst.description=this.description;
		return rst;
	}

	private String mediaId;
	private String title;
	private String description;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		VideoMessageRep other = (VideoMessageRep) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VideoMessageRep [mediaId=" + mediaId + ", title=" + title + ", description=" + description
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + "]";
	}

}
