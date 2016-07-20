package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 * 素材记录数
 * @author Roy Huang
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class MaterialCountRep {

	private Integer totalCount;
	private Integer imageCount;
	private Integer voiceCount;
	private Integer videoCount;
	private Integer fileCount;
	private Integer mpnewsCount;
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getImageCount() {
		return imageCount;
	}
	public void setImageCount(Integer imageCount) {
		this.imageCount = imageCount;
	}
	public Integer getVoiceCount() {
		return voiceCount;
	}
	public void setVoiceCount(Integer voiceCount) {
		this.voiceCount = voiceCount;
	}
	public Integer getVideoCount() {
		return videoCount;
	}
	public void setVideoCount(Integer videoCount) {
		this.videoCount = videoCount;
	}
	public Integer getFileCount() {
		return fileCount;
	}
	public void setFileCount(Integer fileCount) {
		this.fileCount = fileCount;
	}
	public Integer getMpnewsCount() {
		return mpnewsCount;
	}
	public void setMpnewsCount(Integer mpnewsCount) {
		this.mpnewsCount = mpnewsCount;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fileCount == null) ? 0 : fileCount.hashCode());
		result = prime * result + ((imageCount == null) ? 0 : imageCount.hashCode());
		result = prime * result + ((mpnewsCount == null) ? 0 : mpnewsCount.hashCode());
		result = prime * result + ((totalCount == null) ? 0 : totalCount.hashCode());
		result = prime * result + ((videoCount == null) ? 0 : videoCount.hashCode());
		result = prime * result + ((voiceCount == null) ? 0 : voiceCount.hashCode());
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
		MaterialCountRep other = (MaterialCountRep) obj;
		if (fileCount == null) {
			if (other.fileCount != null)
				return false;
		} else if (!fileCount.equals(other.fileCount))
			return false;
		if (imageCount == null) {
			if (other.imageCount != null)
				return false;
		} else if (!imageCount.equals(other.imageCount))
			return false;
		if (mpnewsCount == null) {
			if (other.mpnewsCount != null)
				return false;
		} else if (!mpnewsCount.equals(other.mpnewsCount))
			return false;
		if (totalCount == null) {
			if (other.totalCount != null)
				return false;
		} else if (!totalCount.equals(other.totalCount))
			return false;
		if (videoCount == null) {
			if (other.videoCount != null)
				return false;
		} else if (!videoCount.equals(other.videoCount))
			return false;
		if (voiceCount == null) {
			if (other.voiceCount != null)
				return false;
		} else if (!voiceCount.equals(other.voiceCount))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "MaterialCountRep [totalCount=" + totalCount + ", imageCount=" + imageCount + ", voiceCount="
				+ voiceCount + ", videoCount=" + videoCount + ", fileCount=" + fileCount + ", mpnewsCount="
				+ mpnewsCount + "]";
	}
	
	
}
