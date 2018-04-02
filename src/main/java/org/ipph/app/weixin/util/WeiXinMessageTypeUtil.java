package org.ipph.app.weixin.util;

import org.ipph.app.weixin.enumeration.EventTypeEnum;
import org.ipph.app.weixin.enumeration.MessageTypeEnum;
import org.ipph.app.weixin.model.message.EventMessageModel;
import org.ipph.app.weixin.model.message.MessageModel;

public class WeiXinMessageTypeUtil {
	/**
	 * 获取消息类型
	 * @param message
	 * @return
	 */
	public static MessageTypeEnum getByMessage(MessageModel message) {
		String type=message.getMsgType();
		return getByMsgType(type);
	}
	/**
	 * 根据类型字符串获取枚举值
	 * @param type
	 * @return
	 */
	public static MessageTypeEnum getByMsgType(String type) {
		if(null==type||"".equals(type)) return null;
		
		return MessageTypeEnum.valueOf(type.toUpperCase());
	}
	
	/**
	 * 获取事件类型
	 * @param message
	 * @return
	 */
	public static EventTypeEnum getByEventMessage(MessageModel message) {
		
		if(!(message instanceof EventMessageModel)) return null;
		
		String type=((EventMessageModel)message).getEvent();
		
		return getByEvent(type);
	}
	/**
	 * 根据事件类型字符串获取枚举值
	 * @param type
	 * @return
	 */
	public static EventTypeEnum getByEvent(String type) {
		
		if(null==type||"".equals(type)) return null;
		
		return EventTypeEnum.valueOf(type.toUpperCase());
	}
}
