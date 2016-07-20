package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 扫码且弹出“消息接收中”事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class ScanCodeWaitMsgEventReq extends AbstractScanCodeEventReq{
	

	@Override
	public String toString() {
		return "ScanCodeWaitMsgEventReq [eventKey=" + eventKey + ", scanCodeInfo=" + scanCodeInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

}
