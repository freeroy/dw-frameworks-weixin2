package org.developerworld.frameworks.weixin2.qy.api.dto.enums;

import java.util.HashMap;
import java.util.Map;

import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq.FileChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq.ImageChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq.LinkChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq.TextChatMessageReq;
import org.developerworld.frameworks.weixin2.qy.api.dto.req.ChatMessageReq.VoiceChatMessageReq;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 聊天消息类型
 * @author Roy Huang
 *
 */
public enum ChatMsgType {

	TEXT("text"), IMAGE("image"), VOICE("voice"), LINK("link"), FILE("file");

	private String value;

	ChatMsgType(String value) {
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

	private final static Map<Class<? extends ChatMessageReq>, ChatMsgType> chatMessageReq2MsgType = new HashMap<Class<? extends ChatMessageReq>, ChatMsgType>();

	static {
		chatMessageReq2MsgType.put(TextChatMessageReq.class, TEXT);
		chatMessageReq2MsgType.put(FileChatMessageReq.class, FILE);
		chatMessageReq2MsgType.put(ImageChatMessageReq.class, IMAGE);
		chatMessageReq2MsgType.put(VoiceChatMessageReq.class, VOICE);
		chatMessageReq2MsgType.put(LinkChatMessageReq.class, LINK);
	}

	public static ChatMsgType valueOfChatMessageReqClass(Class<? extends ChatMessageReq> chatMessageReqClass) {
		return chatMessageReq2MsgType.get(chatMessageReqClass);
	}
}
