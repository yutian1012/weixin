package org.ipph.app.weixin.message.response;

import org.ipph.app.weixin.enumeration.EventTypeEnum;
import org.ipph.app.weixin.enumeration.MessageResponseEnum;
import org.ipph.app.weixin.enumeration.MessageTypeEnum;
import org.ipph.app.weixin.model.message.MessageModel;
import org.springframework.stereotype.Component;
import org.ipph.app.weixin.util.WeiXinMessageTypeUtil;

@Component
public class WeixinMessageResponseContext implements IMessageResponse{

	@Override
	public String responseMessage(MessageModel message) {
		MessageTypeEnum type=WeiXinMessageTypeUtil.getByMessage(message);
		
		switch (type) {
		case TEXT://文本消息类型
			message.setDealType(MessageTypeEnum.TEXT.getName());
			break;
		case IMAGE://图文消息类型
			
			break;
		case EVENT://事件消息类型
			return responseEventMessage(message);
			
		default:
			break;
		}
		return getResponseMessage(message);
	}
	
	/**
	 * 返回事件响应消息
	 * @param message
	 * @return
	 */
	private String responseEventMessage(MessageModel message) {
		EventTypeEnum type=WeiXinMessageTypeUtil.getByEventMessage(message);
		
		switch (type) {
		case SUBSCRIBE://关注事件
			message.setDealType(EventTypeEnum.SUBSCRIBE.getName());//设置处理关键字
			break;
		case UNSUBSCRIBE://取消关注事件
			
			break;

		default:
			break;
		}
		return getResponseMessage(message);
	}
	
	/**
	 * 返回消息
	 * @param message
	 * @return
	 */
	private String getResponseMessage(MessageModel message) {
		
		String dealType=message.getDealType();
		
		IMessageResponse messageResponse=null;
		
		MessageResponseEnum responseEnum=MessageResponseEnum.TEXT;
		boolean flag=false;
		for(MessageResponseEnum response:MessageResponseEnum.values()) {
			if(null==dealType||"".equals(dealType)) break;
			
			String[] dealContent=response.getDealContent();
			if(null!=dealContent) {
				for(String dealText:dealContent) {
					if(dealType.equals(dealText)) {
						responseEnum=response;
						flag=true;
						break;
					}
				}
			}
			
			if(flag) break;
		}
		
		if(null!=responseEnum) {
			messageResponse=responseEnum.getMessageResponse();
		}
		
		if(null!=messageResponse) {
			return messageResponse.responseMessage(message);
		}
		
		return null;
	}
	
}
