package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出系统拍照发图的事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class PicSysPhotoEventReq extends AbstractPicEventReq {
	

	@Override
	public String toString() {
		return "PicSysPhotoEventReq [eventKey=" + eventKey + ", sendPicsInfo=" + sendPicsInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}
	
}
