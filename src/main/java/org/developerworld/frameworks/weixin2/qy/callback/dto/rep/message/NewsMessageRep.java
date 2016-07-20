package org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * news详细响应
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class NewsMessageRep extends AbstractMessageRep {

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class NewsArticle {
		@XmlElement(name="Title")
		private String title;
		@XmlElement(name="Description")
		private String description;
		@XmlElement(name="PicUrl")
		private String picUrl;
		@XmlElement(name="Url")
		private String url;

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

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((description == null) ? 0 : description.hashCode());
			result = prime * result + ((picUrl == null) ? 0 : picUrl.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((url == null) ? 0 : url.hashCode());
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
			NewsArticle other = (NewsArticle) obj;
			if (description == null) {
				if (other.description != null)
					return false;
			} else if (!description.equals(other.description))
				return false;
			if (picUrl == null) {
				if (other.picUrl != null)
					return false;
			} else if (!picUrl.equals(other.picUrl))
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
			return "NewsArticle [title=" + title + ", description=" + description + ", picUrl=" + picUrl + ", url="
					+ url + "]";
		}

	}

	@XmlElement(name="ArticleCount")
	private Integer articleCount;
	@XmlElementWrapper(name="Articles")
	@XmlElement(name="item")
	private List<NewsArticle> articles;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<NewsArticle> getArticles() {
		return articles;
	}

	public void setArticles(List<NewsArticle> articles) {
		this.articles = articles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((articleCount == null) ? 0 : articleCount.hashCode());
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
		NewsMessageRep other = (NewsMessageRep) obj;
		if (articleCount == null) {
			if (other.articleCount != null)
				return false;
		} else if (!articleCount.equals(other.articleCount))
			return false;
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "NewsMessageRep [articleCount=" + articleCount + ", articles=" + articles + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType + "]";
	}

}
