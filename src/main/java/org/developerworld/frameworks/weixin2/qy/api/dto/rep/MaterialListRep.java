package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.List;

import org.developerworld.frameworks.weixin2.commons.api.dto.SingleObjectListToListDeserializer;
import org.developerworld.frameworks.weixin2.qy.api.dto.enums.MediaType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 素材列表
 * 
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MaterialListRep {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class Item {
		private String mediaId;
		private String filename;
		private Long updateTime;
		//TODO 该处与微信api文档说明不一致
		@JsonProperty("content")
		@JsonDeserialize(using = SingleObjectListToListDeserializer.class)
		private List<MpnewsArticleRep> articles;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public Long getUpdateTime() {
			return updateTime;
		}

		public void setUpdateTime(Long updateTime) {
			this.updateTime = updateTime;
		}

		public List<MpnewsArticleRep> getArticles() {
			return articles;
		}

		public void setArticles(List<MpnewsArticleRep> articles) {
			this.articles = articles;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((articles == null) ? 0 : articles.hashCode());
			result = prime * result + ((filename == null) ? 0 : filename.hashCode());
			result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
			result = prime * result + ((updateTime == null) ? 0 : updateTime.hashCode());
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
			Item other = (Item) obj;
			if (articles == null) {
				if (other.articles != null)
					return false;
			} else if (!articles.equals(other.articles))
				return false;
			if (filename == null) {
				if (other.filename != null)
					return false;
			} else if (!filename.equals(other.filename))
				return false;
			if (mediaId == null) {
				if (other.mediaId != null)
					return false;
			} else if (!mediaId.equals(other.mediaId))
				return false;
			if (updateTime == null) {
				if (other.updateTime != null)
					return false;
			} else if (!updateTime.equals(other.updateTime))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "Item [mediaId=" + mediaId + ", filename=" + filename + ", updateTime=" + updateTime + ", articles="
					+ articles + "]";
		}

	}

	private MediaType type;
	private Integer totalCount;
	private Integer itemCount;
	private List<Item> itemlist;

	public MediaType getType() {
		return type;
	}

	public void setType(MediaType type) {
		this.type = type;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getItemCount() {
		return itemCount;
	}

	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}

	public List<Item> getItemlist() {
		return itemlist;
	}

	public void setItemlist(List<Item> itemlist) {
		this.itemlist = itemlist;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemCount == null) ? 0 : itemCount.hashCode());
		result = prime * result + ((itemlist == null) ? 0 : itemlist.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MaterialListRep other = (MaterialListRep) obj;
		if (itemCount == null) {
			if (other.itemCount != null)
				return false;
		} else if (!itemCount.equals(other.itemCount))
			return false;
		if (itemlist == null) {
			if (other.itemlist != null)
				return false;
		} else if (!itemlist.equals(other.itemlist))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MaterialList [type=" + type + ", totalCount=" + totalCount + ", itemCount=" + itemCount + ", itemlist="
				+ itemlist + "]";
	}

}
