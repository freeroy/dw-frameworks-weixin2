package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
/**
 * 发图事件请求
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractPicEventReq extends AbstractEventReq {
	
	@XmlAccessorType(XmlAccessType.FIELD)  
	@XmlRootElement
	public static class SendPicsInfo {
		
		public static class PicListXmlAdapter extends XmlAdapter<Object,List<String>>{

			@Override
			public List<String> unmarshal(Object obj) throws Exception {
				List<String> rst=null;
				if(obj instanceof Element){
					rst=new ArrayList<String>();
					Element element=(Element)obj;
					//item
					NodeList itemList=element.getChildNodes();
					for(int i=0;i<itemList.getLength();i++){
						if(itemList.item(i)!=null && itemList.item(i).getFirstChild()!=null)
							//PicMd5Sum
							rst.add(itemList.item(i).getFirstChild().getTextContent());
					}
				}
				return rst;
			}

			@Override
			public Object marshal(List<String> picList) throws Exception {
				// 当前无需求不执行任何操作
				return null;
			}
			
		}
		
		@XmlElement(name = "Count")
		private Integer count;

		@XmlElement(name="PicList")
		@XmlJavaTypeAdapter(PicListXmlAdapter.class)
		private List<String> picMd5SumList;

		public Integer getCount() {
			return count;
		}

		public List<String> getPicMd5SumList() {
			return picMd5SumList;
		}

		public void setCount(Integer count) {
			this.count = count;
		}

		public void setPicMd5SumList(List<String> picMd5SumList) {
			this.picMd5SumList = picMd5SumList;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((count == null) ? 0 : count.hashCode());
			result = prime * result + ((picMd5SumList == null) ? 0 : picMd5SumList.hashCode());
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
			SendPicsInfo other = (SendPicsInfo) obj;
			if (count == null) {
				if (other.count != null)
					return false;
			} else if (!count.equals(other.count))
				return false;
			if (picMd5SumList == null) {
				if (other.picMd5SumList != null)
					return false;
			} else if (!picMd5SumList.equals(other.picMd5SumList))
				return false;
			return true;
		}

		@Override
		public String toString() {
			return "SendPicsInfo [count=" + count + ", picMd5SumList=" + picMd5SumList + "]";
		}

	}

	@XmlElement(name = "EventKey")
	protected String eventKey;

	@XmlElement(name = "SendPicsInfo")
	protected SendPicsInfo sendPicsInfo;

	public String getEventKey() {
		return eventKey;
	}

	public SendPicsInfo getSendPicsInfo() {
		return sendPicsInfo;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public void setSendPicsInfo(SendPicsInfo sendPicsInfo) {
		this.sendPicsInfo = sendPicsInfo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((eventKey == null) ? 0 : eventKey.hashCode());
		result = prime * result + ((sendPicsInfo == null) ? 0 : sendPicsInfo.hashCode());
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
		AbstractPicEventReq other = (AbstractPicEventReq) obj;
		if (eventKey == null) {
			if (other.eventKey != null)
				return false;
		} else if (!eventKey.equals(other.eventKey))
			return false;
		if (sendPicsInfo == null) {
			if (other.sendPicsInfo != null)
				return false;
		} else if (!sendPicsInfo.equals(other.sendPicsInfo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "AbstractPicEventReq [eventKey=" + eventKey + ", sendPicsInfo=" + sendPicsInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
