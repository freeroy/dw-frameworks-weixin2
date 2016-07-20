package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.ChatMsgType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class ChatMessageReq {

	public ChatMessageReq() {
		this.msgtype = ChatMsgType.valueOfChatMessageReqClass(this.getClass());
	}

	@JsonIgnore
	protected ChatMsgType msgtype;

	public ChatMsgType getMsgtype() {
		return msgtype;
	}

	// public void setMsgtype(ChatMsgType msgtype) {
	// this.msgtype = msgtype;
	// }

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
		ChatMessageReq other = (ChatMessageReq) obj;
		if (msgtype != other.msgtype)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ChatMessageReq [msgType=" + msgtype + "]";
	}
	
	public static class TextChatMessageReq extends ChatMessageReq {
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
			TextChatMessageReq other = (TextChatMessageReq) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TextChatMessageReq [content=" + content + ", msgtype=" + msgtype + "]";
		}

	}
	
	public static class ImageChatMessageReq extends ChatMessageReq {
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
			ImageChatMessageReq other = (ImageChatMessageReq) obj;
			if (mediaId == null) {
				if (other.mediaId != null)
					return false;
			} else if (!mediaId.equals(other.mediaId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ImageChatMessageReq [mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}
	
	public static class FileChatMessageReq extends ImageChatMessageReq {

		@Override
		public String toString() {
			return "FileChatMessageReq [msgtype=" + msgtype + "]";
		}

	}
	
	public static class VoiceChatMessageReq extends ImageChatMessageReq {

		@Override
		public String toString() {
			return "VoiceChatMessageReq [msgtype=" + msgtype + "]";
		}

	}

	public static class LinkChatMessageReq extends ChatMessageReq {
		private String title;
		private String description;
		private String url;
		private String thumbMediaId;

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

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getThumbMediaId() {
			return thumbMediaId;
		}

		public void setThumbMediaId(String thumbMediaId) {
			this.thumbMediaId = thumbMediaId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((thumbMediaId == null) ? 0 : thumbMediaId.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((url == null) ? 0 : url.hashCode());
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
			LinkChatMessageReq other = (LinkChatMessageReq) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (thumbMediaId == null) {
				if (other.thumbMediaId != null)
					return false;
			} else if (!thumbMediaId.equals(other.thumbMediaId))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (url == null) {
				if (other.url != null)
					return false;
			} else if (!url.equals(other.url))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "LinkChatMessageReq [title=" + title + ", description=" + description + ", url=" + url
					+ ", thumbMediaId=" + thumbMediaId + ", msgtype=" + msgtype + "]";
		}

	}
}
