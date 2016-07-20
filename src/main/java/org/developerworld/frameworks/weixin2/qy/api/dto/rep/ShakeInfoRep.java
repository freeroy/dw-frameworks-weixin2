package org.developerworld.frameworks.weixin2.qy.api.dto.rep;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class ShakeInfoRep {

	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
	public static class BeaconInfo {
		
		private Long distance;
		private Integer major;
		private Integer minor;
		private String uuid;

		public Long getDistance() {
			return distance;
		}

		public void setDistance(Long distance) {
			this.distance = distance;
		}

		public Integer getMajor() {
			return major;
		}

		public void setMajor(Integer major) {
			this.major = major;
		}

		public Integer getMinor() {
			return minor;
		}

		public void setMinor(Integer minor) {
			this.minor = minor;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((distance == null) ? 0 : distance.hashCode());
			result = prime * result + ((major == null) ? 0 : major.hashCode());
			result = prime * result + ((minor == null) ? 0 : minor.hashCode());
			result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
			BeaconInfo other = (BeaconInfo) obj;
			if (distance == null) {
				if (other.distance != null)
					return false;
			} else if (!distance.equals(other.distance))
				return false;
			if (major == null) {
				if (other.major != null)
					return false;
			} else if (!major.equals(other.major))
				return false;
			if (minor == null) {
				if (other.minor != null)
					return false;
			} else if (!minor.equals(other.minor))
				return false;
			if (uuid == null) {
				if (other.uuid != null)
					return false;
			} else if (!uuid.equals(other.uuid))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "BeaconInfo [distance=" + distance + ", major=" + major + ", minor=" + minor + ", uuid=" + uuid
					+ "]";
		}

	}

	private Integer pageId;
	private String userid;
	private String openid;
	private BeaconInfo beaconInfo;

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public BeaconInfo getBeaconInfo() {
		return beaconInfo;
	}

	public void setBeaconInfo(BeaconInfo beaconInfo) {
		this.beaconInfo = beaconInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beaconInfo == null) ? 0 : beaconInfo.hashCode());
		result = prime * result + ((openid == null) ? 0 : openid.hashCode());
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		result = prime * result + ((userid == null) ? 0 : userid.hashCode());
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
		ShakeInfoRep other = (ShakeInfoRep) obj;
		if (beaconInfo == null) {
			if (other.beaconInfo != null)
				return false;
		} else if (!beaconInfo.equals(other.beaconInfo))
			return false;
		if (openid == null) {
			if (other.openid != null)
				return false;
		} else if (!openid.equals(other.openid))
			return false;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		if (userid == null) {
			if (other.userid != null)
				return false;
		} else if (!userid.equals(other.userid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShakeInfoRep [pageId=" + pageId + ", userid=" + userid + ", openid=" + openid + ", beaconInfo="
				+ beaconInfo + "]";
	}

}
