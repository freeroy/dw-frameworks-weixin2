package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 扫码事件请求
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractScanCodeEventReq extends AbstractEventReq {

	@XmlAccessorType(XmlAccessType.FIELD)  
	@XmlRootElement
	public static class ScanCodeInfo {
		
		@XmlElement(name="ScanType")
		private String scanType;
		@XmlElement(name="ScanResult")
		private String scanResult;

		public String getScanType() {
			return scanType;
		}

		public void setScanType(String scanType) {
			this.scanType = scanType;
		}

		public void setScanResult(String scanResult) {
			this.scanResult = scanResult;
		}

		public String getScanResult() {
			return scanResult;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((scanResult == null) ? 0 : scanResult.hashCode());
			result = prime * result + ((scanType == null) ? 0 : scanType.hashCode());
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
			ScanCodeInfo other = (ScanCodeInfo) obj;
			if (scanResult == null) {
				if (other.scanResult != null)
					return false;
			} else if (!scanResult.equals(other.scanResult))
				return false;
			if (scanType == null) {
				if (other.scanType != null)
					return false;
			} else if (!scanType.equals(other.scanType))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "ScanCodeInfo [scanType=" + scanType + ", scanResult=" + scanResult + "]";
		}

	}

	@XmlElement(name="EventKey")
	protected String eventKey;
	@XmlElement(name="ScanCodeInfo")
	protected ScanCodeInfo scanCodeInfo;

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
		this.scanCodeInfo = scanCodeInfo;
	}

	public String getEventKey() {
		return eventKey;
	}

	public ScanCodeInfo getScanCodeInfo() {
		return scanCodeInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventKey == null) ? 0 : eventKey.hashCode());
		result = prime * result + ((scanCodeInfo == null) ? 0 : scanCodeInfo.hashCode());
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
		AbstractScanCodeEventReq other = (AbstractScanCodeEventReq) obj;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		if (scanCodeInfo == null) {
			if (other.scanCodeInfo != null)
				return false;
		} else if (!scanCodeInfo.equals(other.scanCodeInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractScanCodeEventReq [eventKey=" + eventKey + ", scanCodeInfo=" + scanCodeInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
