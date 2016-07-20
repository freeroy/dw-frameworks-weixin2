package org.developerworld.frameworks.weixin2.qy.callback.dto.req.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 弹出拍照或者相册发图的事件请求
 * @author Roy Huang
 *
 */
@XmlRootElement(name="xml")
public class PicPhotoOrAlbumEventReq extends AbstractPicEventReq{
	
	@Override
	public String toString() {
		return "PicPhotoOrAlbumEventReq [eventKey=" + eventKey + ", sendPicsInfo=" + sendPicsInfo + ", event=" + event
				+ ", toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + ", agentID=" + agentID + "]";
	}

	
}
