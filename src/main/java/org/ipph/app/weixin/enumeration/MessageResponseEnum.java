package org.ipph.app.weixin.enumeration;

import org.ipph.app.weixin.message.response.IMessageResponse;
import org.ipph.app.weixin.message.response.TextMessageResponseImpl;
import org.ipph.app.weixin.message.response.SubscribeMessageResponseImpl;

public enum MessageResponseEnum {
	TEXT("text",new TextMessageResponseImpl(),null),
	SUSCRIBE("subscribe",new SubscribeMessageResponseImpl(),new String[] {"subscribe"});
	
	private String name;
	private IMessageResponse messageResponseImpl=null;
	private String[] dealContent=null;
	private MessageResponseEnum(String name,IMessageResponse messageResponse,String[] dealContent) {
		this.name=name;
		this.messageResponseImpl=messageResponse;
		this.dealContent=dealContent;
	}
	public String getName() {
		return this.name;
	}
	public IMessageResponse getMessageResponse() {
		return this.messageResponseImpl;
	}
	public String[] getDealContent() {
		return this.dealContent;
	}
}
