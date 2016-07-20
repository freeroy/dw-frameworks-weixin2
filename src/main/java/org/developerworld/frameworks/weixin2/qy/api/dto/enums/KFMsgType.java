package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import java.util.HashMap;
import java.util.Map;

import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq.FileKFMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq.ImageKFMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq.TextKFMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.KFMessageReq.VoiceKFMessageReq;

import com.fasterxml.jackson.annotation.JsonValue;

public enum KFMsgType {
	TEXT("text"), IMAGE("image"), VOICE("voice"), FILE("file");

	private String value;

	KFMsgType(String value) {
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

	private final static Map<Class<? extends KFMessageReq>, KFMsgType> kfMessageReq2MsgType = new HashMap<Class<? extends KFMessageReq>, KFMsgType>();

	static {
		kfMessageReq2MsgType.put(TextKFMessageReq.class, TEXT);
		kfMessageReq2MsgType.put(FileKFMessageReq.class, FILE);
		kfMessageReq2MsgType.put(ImageKFMessageReq.class, IMAGE);
		kfMessageReq2MsgType.put(VoiceKFMessageReq.class, VOICE);
	}

	public static KFMsgType valueOfKFMessageReqClass(Class<? extends KFMessageReq> kfMessageReqClass) {
		return kfMessageReq2MsgType.get(kfMessageReqClass);
	}
}
