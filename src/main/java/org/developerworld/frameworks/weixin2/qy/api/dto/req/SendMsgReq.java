package org.developerworld.frameworks.weixin2.qy.api.dto.req;

import java.util.List;

import org.developerworld.frameworks.weixin2.qy.api.dto.enums.SendMsgType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 发送消息
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class SendMsgReq {

	public SendMsgReq() {
		this.msgtype = SendMsgType.valueOfSendMsgReqClass(this.getClass());
	}

	@JsonIgnore
	protected SendMsgType msgtype;

//	public void setMsgtype(MsgType msgtype) {
//		this.msgtype = msgtype;
//	}

	public SendMsgType getMsgtype() {
		return msgtype;
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
		SendMsgReq other = (SendMsgReq) obj;
		if (msgtype != other.msgtype)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SendMsgReq [msgtype=" + msgtype + "]";
	}

	public static class TextSendMsgReq extends SendMsgReq {

		private String content;

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((content == null) ? 0 : content.hashCode());
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
			TextSendMsgReq other = (TextSendMsgReq) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "TextSendMsgReq [content=" + content + ", msgtype=" + msgtype + "]";
		}

	}

	public static class FileMediaSendMsgReq extends SendMsgReq {

		protected String mediaId;

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getMediaId() {
			return mediaId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
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
			FileMediaSendMsgReq other = (FileMediaSendMsgReq) obj;
			if (mediaId == null) {
				if (other.mediaId != null)
					return false;
			} else if (!mediaId.equals(other.mediaId))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "FileMediaSendMsgReq [mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}

	public static class ImageMediaSendMsgReq extends FileMediaSendMsgReq {

		@Override
		public String toString() {
			return "ImageMediaSendMsgReq [mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}

	public static class VoiceMediaSendMsgReq extends FileMediaSendMsgReq {

		@Override
		public String toString() {
			return "VoiceMediaSendMsgReq [mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}

	public static class VideoMediaSendMsgReq extends FileMediaSendMsgReq {

		private String title;
		private String description;

		public void setTitle(String title) {
			this.title = title;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((description == null) ? 0 : description.hashCode());
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
			VideoMediaSendMsgReq other = (VideoMediaSendMsgReq) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
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
			return "VideoMediaSendMsgReq [title=" + title + ", description=" + description + ", mediaId=" + mediaId
					+ ", msgtype=" + msgtype + "]";
		}

	}

	public static class NewsSendMsgReq extends SendMsgReq {

		private List<NewsArticleReq> articles;

		public void setArticles(List<NewsArticleReq> articles) {
			this.articles = articles;
		}

		public List<NewsArticleReq> getArticles() {
			return articles;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((articles == null) ? 0 : articles.hashCode());
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
			NewsSendMsgReq other = (NewsSendMsgReq) obj;
			if (articles == null) {
				if (other.articles != null)
					return false;
			} else if (!articles.equals(other.articles))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "NewsSendMsgReq [articles=" + articles + ", msgtype=" + msgtype + "]";
		}

	}

	public static class MpnewsSendMsgReq extends FileMediaSendMsgReq {

		private List<MpnewsArticleReq> articles;

		public void setArticles(List<MpnewsArticleReq> articles) {
			this.articles = articles;
		}

		public List<MpnewsArticleReq> getArticles() {
			return articles;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = super.hashCode();
			result = prime * result + ((articles == null) ? 0 : articles.hashCode());
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
			MpnewsSendMsgReq other = (MpnewsSendMsgReq) obj;
			if (articles == null) {
				if (other.articles != null)
					return false;
			} else if (!articles.equals(other.articles))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "MpnewsSendMsgReq [articles=" + articles + ", mediaId=" + mediaId + ", msgtype=" + msgtype + "]";
		}

	}
}
