package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 上报地理位置事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class LocationEventReq extends AbstractEventReq {

	@XmlElement(name="Latitude")
	private Double latitude;
	@XmlElement(name="Longitude")
	private Double longitude;
	@XmlElement(name="Precision")
	private Double precision;

	public Double getLatitude() {
		return latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public void setPrecision(Double precision) {
		this.precision = precision;
	}

	public Double getPrecision() {
		return precision;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((latitude == null) ? 0 : latitude.hashCode());
		result = prime * result + ((longitude == null) ? 0 : longitude.hashCode());
		result = prime * result + ((precision == null) ? 0 : precision.hashCode());
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
		LocationEventReq other = (LocationEventReq) obj;
		if (latitude == null) {
			if (other.latitude != null)
				return false;
		} else if (!latitude.equals(other.latitude))
			return false;
		if (longitude == null) {
			if (other.longitude != null)
				return false;
		} else if (!longitude.equals(other.longitude))
			return false;
		if (precision == null) {
			if (other.precision != null)
				return false;
		} else if (!precision.equals(other.precision))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationEventReq [latitude=" + latitude + ", longitude=" + longitude + ", precision=" + precision
				+ ", event=" + event + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime="
				+ createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
