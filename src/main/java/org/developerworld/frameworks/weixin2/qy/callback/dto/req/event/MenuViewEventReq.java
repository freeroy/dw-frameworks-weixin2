package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 菜单点击条状事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class MenuViewEventReq extends AbstractMenuEventReq {

	@Override
	public String toString() {
		return "MenuViewEventReq [eventKey=" + eventKey + ", event=" + event + ", toUserName=" + toUserName
				+ ", fromUserName=" + fromUserName + ", createTime=" + createTime + ", msgType=" + msgType
				+ ", agentID=" + agentID + "]";
	}
	
	
}
