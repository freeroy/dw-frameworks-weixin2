package org.developerworld.frameworks.weixin2.qy.api.dto.base;

import org.developerworld.frameworks.weixin2.commons.api.dto.BooleanToIntegerSerializer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 图文对象
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public abstract class MpnewsArticleBase {

	protected String title;
	protected String thumbMediaId;
	protected String author;
	protected String contentSourceUrl;
	protected String content;
	protected String digest;
	@JsonSerialize(using = BooleanToIntegerSerializer.class)
	protected Boolean showCoverPic;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public Boolean getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(Boolean showCoverPic) {
		this.showCoverPic = showCoverPic;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((author == null) ? 0 : author.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((contentSourceUrl == null) ? 0 : contentSourceUrl.hashCode());
		result = prime * result + ((digest == null) ? 0 : digest.hashCode());
		result = prime * result + ((showCoverPic == null) ? 0 : showCoverPic.hashCode());
		result = prime * result + ((thumbMediaId == null) ? 0 : thumbMediaId.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		MpnewsArticleBase other = (MpnewsArticleBase) obj;
		if (author == null) {
			if (other.author != null)
				return false;
		} else if (!author.equals(other.author))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (contentSourceUrl == null) {
			if (other.contentSourceUrl != null)
				return false;
		} else if (!contentSourceUrl.equals(other.contentSourceUrl))
			return false;
		if (digest == null) {
			if (other.digest != null)
				return false;
		} else if (!digest.equals(other.digest))
			return false;
		if (showCoverPic == null) {
			if (other.showCoverPic != null)
				return false;
		} else if (!showCoverPic.equals(other.showCoverPic))
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
		return true;
	}

	@Override
	public String toString() {
		return "MpnewsArticleBase [title=" + title + ", thumbMediaId=" + thumbMediaId + ", author=" + author
				+ ", contentSourceUrl=" + contentSourceUrl + ", content=" + content + ", digest=" + digest
				+ ", showCoverPic=" + showCoverPic + "]";
	}

}
