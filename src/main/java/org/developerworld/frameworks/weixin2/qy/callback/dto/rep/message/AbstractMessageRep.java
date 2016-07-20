package org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message;

import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.AbstractRep;

/**
 * 响应对象
 * 
 * @author Roy Huang
 *
 */
public abstract class AbstractMessageRep extends AbstractRep {
	
	@Override
	public String toString() {
		return "AbstractMessageRep [toUserName=" + toUserName + ", fromUserName=" + fromUserName + ", createTime=" + createTime
				+ ", msgType=" + msgType + "]";
	}

}
