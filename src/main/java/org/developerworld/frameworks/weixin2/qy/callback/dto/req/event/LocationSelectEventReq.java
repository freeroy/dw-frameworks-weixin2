package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出地理位置选择器的事件请求
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class LocationSelectEventReq extends AbstractEventReq {

	@XmlRootElement
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class SendLocationInfo {
		@XmlElement(name="Location_X")
		private Double locationX;
		@XmlElement(name="Location_Y")
		private Double locationY;
		@XmlElement(name="Scale")
		private Double scale;
		@XmlElement(name="Label")
		private String label;
		@XmlElement(name="Poiname")
		private String poiname;

		public Double getLocationX() {
			return locationX;
		}

		public Double getLocationY() {
			return locationY;
		}

		public void setLocationX(Double locationX) {
			this.locationX = locationX;
		}

		public void setLocationY(Double locationY) {
			this.locationY = locationY;
		}

		public void setScale(Double scale) {
			this.scale = scale;
		}

		public void setLabel(String label) {
			this.label = label;
		}

		public void setPoiname(String poiname) {
			this.poiname = poiname;
		}

		public Double getScale() {
			return scale;
		}

		public String getLabel() {
			return label;
		}

		public String getPoiname() {
			return poiname;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			result = prime * result + ((locationX == null) ? 0 : locationX.hashCode());
			result = prime * result + ((locationY == null) ? 0 : locationY.hashCode());
			result = prime * result + ((poiname == null) ? 0 : poiname.hashCode());
			result = prime * result + ((scale == null) ? 0 : scale.hashCode());
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
			SendLocationInfo other = (SendLocationInfo) obj;
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
			if (poiname == null) {
				if (other.poiname != null)
					return false;
			} else if (!poiname.equals(other.poiname))
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
			return "SendLocationInfo [locationX=" + locationX + ", locationY=" + locationY + ", scale=" + scale
					+ ", label=" + label + ", poiname=" + poiname + "]";
		}

	}

	@XmlElement(name="EventKey")
	private String eventKey;
	@XmlElement(name="SendLocationInfo")
	private SendLocationInfo sendLocationInfo;
	
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public void setSendLocationInfo(SendLocationInfo sendLocationInfo) {
		this.sendLocationInfo = sendLocationInfo;
	}

	public String getEventKey() {
		return eventKey;
	}

	public SendLocationInfo getSendLocationInfo() {
		return sendLocationInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventKey == null) ? 0 : eventKey.hashCode());
		result = prime * result + ((sendLocationInfo == null) ? 0 : sendLocationInfo.hashCode());
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
		LocationSelectEventReq other = (LocationSelectEventReq) obj;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		if (sendLocationInfo == null) {
			if (other.sendLocationInfo != null)
				return false;
		} else if (!sendLocationInfo.equals(other.sendLocationInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LocationSelectEventReq [eventKey=" + eventKey + ", sendLocationInfo=" + sendLocationInfo + ", event="
				+ event + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
