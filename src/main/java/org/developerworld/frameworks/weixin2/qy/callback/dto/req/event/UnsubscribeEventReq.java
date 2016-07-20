package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 取消关注事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class UnsubscribeEventReq extends AbstractEventReq{

	@Override
	public String toString() {
		return "UnsubscribeEventReq [event=" + event + ", toUserName=" + toUserName + ", fromUserName=" + fromUserName
				+ ", createTime=" + createTime + ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

	
}
