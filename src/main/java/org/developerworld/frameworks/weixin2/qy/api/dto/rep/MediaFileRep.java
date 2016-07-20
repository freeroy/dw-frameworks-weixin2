package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import java.util.Arrays;

/**
 * 下载媒体对象
 * 
 * @author Roy Huang
 *
 */
public class MediaFileRep {

	private String contentType;
	private String contentDisposition;
	private Long contentLength;
	private String filename;
	private byte[] contentBody;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public Long getContentLength() {
		return contentLength;
	}

	public void setContentLength(Long contentLength) {
		this.contentLength = contentLength;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public byte[] getContentBody() {
		return contentBody;
	}

	public void setContentBody(byte[] contentBody) {
		this.contentBody = contentBody;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(contentBody);
		result = prime * result + ((contentDisposition == null) ? 0 : contentDisposition.hashCode());
		result = prime * result + ((contentLength == null) ? 0 : contentLength.hashCode());
		result = prime * result + ((contentType == null) ? 0 : contentType.hashCode());
		result = prime * result + ((filename == null) ? 0 : filename.hashCode());
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
		MediaFileRep other = (MediaFileRep) obj;
		if (!Arrays.equals(contentBody, other.contentBody))
			return false;
		if (contentDisposition == null) {
			if (other.contentDisposition != null)
				return false;
		} else if (!contentDisposition.equals(other.contentDisposition))
			return false;
		if (contentLength == null) {
			if (other.contentLength != null)
				return false;
		} else if (!contentLength.equals(other.contentLength))
			return false;
		if (contentType == null) {
			if (other.contentType != null)
				return false;
		} else if (!contentType.equals(other.contentType))
			return false;
		if (filename == null) {
			if (other.filename != null)
				return false;
		} else if (!filename.equals(other.filename))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MediaFileRep [contentType=" + contentType + ", contentDisposition=" + contentDisposition
				+ ", contentLength=" + contentLength + ", filename=" + filename + "]";
	}

}
