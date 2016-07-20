package org.developerworld.frameworks.weixin2.qy.callback.dto.enums;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.commons.collections.MapUtils;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.AbstractRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.ImageMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.NewsMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.TextMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.VideoMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.message.VoiceMessageRep;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.AbstractEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.BatchJobResultEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.EnterAgentEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.LocationEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.LocationSelectEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.MenuClickEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.MenuViewEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.PicPhotoOrAlbumEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.PicSysPhotoEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.PicWeiXinEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.ScanCodePushEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.ScanCodeWaitMsgEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.SubscribeEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.event.UnsubscribeEventReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.ImageMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.LinkMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.LocationMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.ShortVideoMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.TextMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.VideoMessageReq;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.message.VoiceMessageReq;

/**
 * 消息类型
 * 
 * @author Roy Huang
 *
 */
@XmlEnum(String.class)
public enum MsgType {

	@XmlEnumValue("text") TEXT("text"), @XmlEnumValue("image") IMAGE("image"), @XmlEnumValue("voice") VOICE(
			"voice"), @XmlEnumValue("video") VIDEO("video"), @XmlEnumValue("shortvideo") SHORTVIDEO(
					"shortvideo"), @XmlEnumValue("news") NEWS("news"), @XmlEnumValue("location") LOCATION(
							"location"), @XmlEnumValue("link") LINK("link"), @XmlEnumValue("event") EVENT("event");

	private String value;

	MsgType(String value) {
		this.value = value;
	}

	public String value() {
		return value;
	}

	@Override
	public String toString() {
		return value;
	}

	/**
	 * 根据消息对象获取枚举
	 * 
	 * @param reqCalss
	 * @return
	 */
	public static MsgType valueOfReqClass(Class<? extends AbstractReq> reqCalss) {
		return req2MsgTypeMapping.get(reqCalss);
	}

	/**
	 * 根据消息对象获取枚举
	 * 
	 * @param repCalss
	 * @return
	 */
	public static MsgType valueOfRepClass(Class<? extends AbstractRep> repCalss) {
		return rep2MsgTypeMapping.get(repCalss);
	}

	/**
	 * 获取消息对象类型
	 * 
	 * @return
	 */
	public Class<? extends AbstractReq> getReqClass() {
		return getReqClass(this);
	}

	/**
	 * 根据消息类型获取消息对象类型
	 * 
	 * @param msgType
	 * @return
	 */
	public static Class<? extends AbstractReq> getReqClass(MsgType msgType) {
		return msgType2ReqMapping.get(msgType);
	}

	/**
	 * 根据值获取对象
	 * 
	 * @param value
	 * @return
	 */
	public static MsgType valueOfValue(String value) {
		for (MsgType msgType : MsgType.values())
			if (msgType.value.equals(value))
				return msgType;
		return null;
	}

	private static Map<Class<? extends AbstractReq>, MsgType> req2MsgTypeMapping = new HashMap<Class<? extends AbstractReq>, MsgType>();
	private static Map<MsgType, Class<? extends AbstractReq>> msgType2ReqMapping = new HashMap<MsgType, Class<? extends AbstractReq>>();
	private static Map<Class<? extends AbstractRep>, MsgType> rep2MsgTypeMapping = new HashMap<Class<? extends AbstractRep>, MsgType>();

	static {
		req2MsgTypeMapping.put(ImageMessageReq.class, IMAGE);
		req2MsgTypeMapping.put(LinkMessageReq.class, LINK);
		req2MsgTypeMapping.put(LocationMessageReq.class, LOCATION);
		req2MsgTypeMapping.put(TextMessageReq.class, TEXT);
		req2MsgTypeMapping.put(VideoMessageReq.class, VIDEO);
		req2MsgTypeMapping.put(ShortVideoMessageReq.class, SHORTVIDEO);
		req2MsgTypeMapping.put(VoiceMessageReq.class, VOICE);

		req2MsgTypeMapping.put(BatchJobResultEventReq.class, EVENT);
		req2MsgTypeMapping.put(EnterAgentEventReq.class, EVENT);
		req2MsgTypeMapping.put(LocationEventReq.class, EVENT);
		req2MsgTypeMapping.put(LocationSelectEventReq.class, EVENT);
		req2MsgTypeMapping.put(MenuClickEventReq.class, EVENT);
		req2MsgTypeMapping.put(MenuViewEventReq.class, EVENT);
		req2MsgTypeMapping.put(PicPhotoOrAlbumEventReq.class, EVENT);
		req2MsgTypeMapping.put(PicSysPhotoEventReq.class, EVENT);
		req2MsgTypeMapping.put(PicWeiXinEventReq.class, EVENT);
		req2MsgTypeMapping.put(ScanCodePushEventReq.class, EVENT);
		req2MsgTypeMapping.put(ScanCodeWaitMsgEventReq.class, EVENT);
		req2MsgTypeMapping.put(SubscribeEventReq.class, EVENT);
		req2MsgTypeMapping.put(UnsubscribeEventReq.class, EVENT);
		// 反转map
		msgType2ReqMapping = MapUtils.invertMap(req2MsgTypeMapping);
		msgType2ReqMapping.put(EVENT, AbstractEventReq.class);

		rep2MsgTypeMapping.put(ImageMessageRep.class, IMAGE);
		rep2MsgTypeMapping.put(NewsMessageRep.class, NEWS);
		rep2MsgTypeMapping.put(TextMessageRep.class, TEXT);
		rep2MsgTypeMapping.put(VideoMessageRep.class, VIDEO);
		rep2MsgTypeMapping.put(VoiceMessageRep.class, VOICE);
	}
}
