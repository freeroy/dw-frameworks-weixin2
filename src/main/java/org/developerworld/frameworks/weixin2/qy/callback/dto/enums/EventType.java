package org.developerworld.frameworks.weixin2.qy.callback.dto.enums;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;

import org.apache.commons.collections.MapUtils;
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

/**
 * 事件类型
 * 
 * @author Roy Huang
 *
 */
@XmlEnum
public enum EventType {

	@XmlEnumValue("subscribe") SUBSCRIBE("subscribe"), @XmlEnumValue("unsubscribe") UNSUBSCRIBE(
			"unsubscribe"), @XmlEnumValue("location") LOCATION("location"), @XmlEnumValue("click") CLICK(
					"click"), @XmlEnumValue("view") VIEW("view"), @XmlEnumValue("scancode_push") SCANCODE_PUSH(
							"scancode_push"), @XmlEnumValue("scancode_waitmsg") SCANCODE_WAITMSG(
									"scancode_waitmsg"), @XmlEnumValue("pic_sysphoto") PIC_SYSPHOTO(
											"pic_sysphoto"), @XmlEnumValue("pic_photo_or_album") PIC_PHOTO_OR_ALBUM(
													"pic_photo_or_album"), @XmlEnumValue("pic_weixin") PIC_WEIXIN(
															"pic_weixin"), @XmlEnumValue("location_select") LOCATION_SELECT(
																	"location_select"), @XmlEnumValue("enter_agent") ENTER_AGENT(
																			"enter_agent"), @XmlEnumValue("batch_job_result") BATCH_JOB_RESULT(
																					"batch_job_result");

	private String value;

	EventType(String value) {
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
	 * 根据事件消息类型获取枚举
	 * 
	 * @param reqClass
	 * @return
	 */
	public static EventType valueOf(Class<? extends AbstractEventReq> reqClass) {
		return eventReq2EventTypeMapping.get(reqClass);
	}

	/**
	 * 获取对应消息类型
	 * 
	 * @return
	 */
	public Class<? extends AbstractEventReq> getEventReqClass() {
		return getEventReqClass(this);
	}

	/**
	 * 根据类型获取消息类型
	 * 
	 * @param eventType
	 * @return
	 */
	public static Class<? extends AbstractEventReq> getEventReqClass(EventType eventType) {
		return eventType2EventReqMapping.get(eventType);
	}

	/**
	 * 根据值获取对象
	 * 
	 * @param value
	 * @return
	 */
	public static EventType valueOfValue(String value) {
		for (EventType eventType : EventType.values())
			if (eventType.value.equals(value))
				return eventType;
		return null;
	}

	protected static Map<Class<? extends AbstractEventReq>, EventType> eventReq2EventTypeMapping = new HashMap<Class<? extends AbstractEventReq>, EventType>();
	protected static Map<EventType, Class<? extends AbstractEventReq>> eventType2EventReqMapping = new HashMap<EventType, Class<? extends AbstractEventReq>>();

	static {
		// 注册绑定
		eventReq2EventTypeMapping.put(BatchJobResultEventReq.class, EventType.BATCH_JOB_RESULT);
		eventReq2EventTypeMapping.put(EnterAgentEventReq.class, EventType.ENTER_AGENT);
		eventReq2EventTypeMapping.put(LocationEventReq.class, EventType.LOCATION);
		eventReq2EventTypeMapping.put(LocationSelectEventReq.class, EventType.LOCATION_SELECT);
		eventReq2EventTypeMapping.put(MenuClickEventReq.class, EventType.CLICK);
		eventReq2EventTypeMapping.put(MenuViewEventReq.class, EventType.VIEW);
		eventReq2EventTypeMapping.put(PicPhotoOrAlbumEventReq.class, EventType.PIC_PHOTO_OR_ALBUM);
		eventReq2EventTypeMapping.put(PicSysPhotoEventReq.class, EventType.PIC_SYSPHOTO);
		eventReq2EventTypeMapping.put(PicWeiXinEventReq.class, EventType.PIC_WEIXIN);
		eventReq2EventTypeMapping.put(ScanCodePushEventReq.class, EventType.SCANCODE_PUSH);
		eventReq2EventTypeMapping.put(ScanCodeWaitMsgEventReq.class, EventType.SCANCODE_WAITMSG);
		eventReq2EventTypeMapping.put(SubscribeEventReq.class, EventType.SUBSCRIBE);
		eventReq2EventTypeMapping.put(UnsubscribeEventReq.class, EventType.UNSUBSCRIBE);
		eventType2EventReqMapping = MapUtils.invertMap(eventReq2EventTypeMapping);
	}
}
