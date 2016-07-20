package org.developerworld.frameworks.weixin2.qy.callback.dto.req.message;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 视频消息请求
 * 
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class VideoMessageReq extends AbstractVideoMessageReq {

	@Override
	public String toString() {
		return "VideoMessageReq [mediaId=" + mediaId + ", thumbMediaId=" + thumbMediaId + ", msgId=" + msgId
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

	
}
