package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import java.util.HashMap;
import java.util.Map;

import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.FileMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.ImageMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.MpnewsSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.NewsSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.TextSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.VideoMediaSendMsgReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.SendMsgReq.VoiceMediaSendMsgReq;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 消息类型
 * 
 * @author Roy Huang
 *
 */
public enum SendMsgType {

	TEXT("text"), IMAGE("image"), VOICE("voice"), VIDEO("video"), FILE("file"), NEWS("news"), MPNEWS("mpnews");

	private String value;

	SendMsgType(String value) {
		this.value = value;
	}

	@JsonValue
	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return this.value;
	}
	
	private final static Map<Class<? extends SendMsgReq>,SendMsgType> sendMsgReq2MsgType=new HashMap<Class<? extends SendMsgReq>,SendMsgType>();
	static{
		sendMsgReq2MsgType.put(TextSendMsgReq.class, SendMsgType.TEXT);
		sendMsgReq2MsgType.put(FileMediaSendMsgReq.class, SendMsgType.FILE);
		sendMsgReq2MsgType.put(ImageMediaSendMsgReq.class, SendMsgType.IMAGE);
		sendMsgReq2MsgType.put(VoiceMediaSendMsgReq.class, SendMsgType.VOICE);
		sendMsgReq2MsgType.put(VideoMediaSendMsgReq.class, SendMsgType.VIDEO);
		sendMsgReq2MsgType.put(NewsSendMsgReq.class, SendMsgType.NEWS);
		sendMsgReq2MsgType.put(MpnewsSendMsgReq.class, SendMsgType.MPNEWS);
	}
	
	/**
	 * 根据发送请求消息获取枚举
	 * @param sendMsgReqClass
	 * @return
	 */
	public static SendMsgType valueOfSendMsgReqClass(Class<? extends SendMsgReq> sendMsgReqClass) {
		return sendMsgReq2MsgType.get(sendMsgReqClass);
	}
}
