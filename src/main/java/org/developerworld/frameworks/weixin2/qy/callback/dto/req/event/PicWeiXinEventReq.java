package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出微信相册发图器的事请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class PicWeiXinEventReq extends AbstractPicEventReq{

	@Override
	public String toString() {
		return "PicWeiXinEventReq [eventKey=" + eventKey + ", sendPicsInfo=" + sendPicsInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
