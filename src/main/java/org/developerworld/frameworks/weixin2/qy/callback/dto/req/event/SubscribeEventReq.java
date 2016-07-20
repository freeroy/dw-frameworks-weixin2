package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 关注事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class SubscribeEventReq extends AbstractEventReq{

	@Override
	public String toString() {
		return "SubscribeEventReq [event=" + event + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
