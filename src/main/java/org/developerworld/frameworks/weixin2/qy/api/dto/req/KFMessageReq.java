package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.KFMsgType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class KFMessageReq {
	
	public KFMessageReq(){
		this.msgtype=KFMsgType.valueOfKFMessageReqClass(this.getClass());
	}

	@JsonIgnore
	protected KFMsgType msgtype;

	public KFMsgType getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(KFMsgType msgtype) {
		this.msgtype = msgtype;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((msgtype == null) ? 0 : msgtype.hashCode());
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
		KFMessageReq other = (KFMessageReq) obj;
		if (msgtype != other.msgtype)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "KFMessageReq [msgtype=" + msgtype + "]";
	}

	public static class TextKFMessageReq extends KFMessageReq {
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
			TextKFMessageReq other = (TextKFMessageReq) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TextKFMessageReq [content=" + content + ", msgtype=" + msgtype + "]";
		}

	}

	public static class ImageKFMessageReq extends KFMessageReq {
		private String mediaId;

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
			ImageKFMessageReq other = (ImageKFMessageReq) obj;
			if (mediaId == null) {
				if (other.mediaId != null)
					return false;
			} else if (!mediaId.equals(other.mediaId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ImageKFMessageReq [mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}

	public static class FileKFMessageReq extends ImageKFMessageReq {

		@Override
		public String toString() {
			return "FileKFMessageReq [msgtype=" + msgtype + "]";
		}

	}

	public static class VoiceKFMessageReq extends KFMessageReq {

		@Override
		public String toString() {
			return "VoiceKFMessageReq [msgtype=" + msgtype + "]";
		}

	}
}
