package org.ipph.app.weixin.message.receive;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;


import org.dom4j.DocumentException;
import org.ipph.app.weixin.enumeration.EventTypeEnum;
import org.ipph.app.weixin.enumeration.MessageTypeEnum;
import org.ipph.app.weixin.model.message.WeixinMessageModel;
import org.ipph.app.weixin.util.Map2EntryConvertUtils;
import org.ipph.app.weixin.util.WeiXinMessageTypeUtil;
import org.ipph.app.weixin.util.WeixinMessageUtil;
import org.ipph.app.weixin.xml.XmlElement;
import org.springframework.stereotype.Component;

@Component
public class WeixinMessageReceiveContext {
	
	public WeixinMessageModel getWeixinMessageModel(InputStream in) throws IOException, DocumentException {
		WeixinMessageModel messageModel=null;
		
		Map<String,String> data=WeixinMessageUtil.xmlToMap(in);
		
		Class<?> clazz=null;
		if(data.size()>0) {
			clazz=getMessageModel(data);
		}
		if(null!=clazz) {
			messageModel=(WeixinMessageModel) Map2EntryConvertUtils.convert(data, clazz, null);
		}
		
		return messageModel;
	}
	
	public WeixinMessageModel getWeixinMessageModel(String xml) throws IOException, DocumentException {
		WeixinMessageModel messageModel=null;
		
		Map<String,String> data=WeixinMessageUtil.xmlToMap(xml);
		
		Class<?> clazz=null;
		if(data.size()>0) {
			clazz=getMessageModel(data);
		}
		if(null!=clazz) {
			messageModel=(WeixinMessageModel) Map2EntryConvertUtils.convert(data, clazz, null);
		}
		
		return messageModel;
	}
	/**
	 * 获取微信消息类型
	 * @param data
	 * @return
	 */
	private Class<?> getMessageModel(Map<String,String> data){
		Class<?> clazz=null;
		if(data.containsKey(XmlElement.MsgType)&&null!=data.get(XmlElement.MsgType)) {
			String msgType=data.get(XmlElement.MsgType);
			
			MessageTypeEnum typeEnum=WeiXinMessageTypeUtil.getByMsgType(msgType);
			
			switch (typeEnum) {
			case EVENT:
				clazz=getEventMessageModel(data.get(XmlElement.Event),data.get(XmlElement.EventKey));
				break;
			default:
				clazz=typeEnum.getMessageModel();
				break;
			}
		}
		return clazz;
	}
	
	/**
	 * 获取事件对应的实体类
	 * @param event
	 * @param eventKey
	 * @return
	 */
	private Class<?> getEventMessageModel(String event,String eventKey){
		if(null==event||"".equals(event)) return null;
		
		EventTypeEnum eventType=WeiXinMessageTypeUtil.getByEvent(event);
		if(null!=eventType) {
			return eventType.getEventModel(eventKey);
		}
		
		return null;
	}
}
