package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * location消息请求
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class LocationMessageReq extends AbstractMessageReq {

	@XmlElement(name = "Location_X")
	private Double locationX;
	@XmlElement(name = "Location_Y")
	private Double locationY;
	@XmlElement(name = "Scale")
	private Integer scale;
	@XmlElement(name = "Label")
	private String label;

	public Double getLocationX() {
		return locationX;
	}

	public Double getLocationY() {
		return locationY;
	}

	public Integer getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLocationX(Double locationX) {
		this.locationX = locationX;
	}

	public void setLocationY(Double locationY) {
		this.locationY = locationY;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((locationX == null) ? 0 : locationX.hashCode());
		result = prime * result + ((locationY == null) ? 0 : locationY.hashCode());
		result = prime * result + ((scale == null) ? 0 : scale.hashCode());
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
		LocationMessageReq other = (LocationMessageReq) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (locationX == null) {
			if (other.locationX != null)
				return false;
		} else if (!locationX.equals(other.locationX))
			return false;
		if (locationY == null) {
			if (other.locationY != null)
				return false;
		} else if (!locationY.equals(other.locationY))
			return false;
		if (scale == null) {
			if (other.scale != null)
				return false;
		} else if (!scale.equals(other.scale))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationMessage [locationX=" + locationX + ", locationY=" + locationY + ", scale=" + scale + ", label="
				+ label + ", msgId=" + msgId + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
