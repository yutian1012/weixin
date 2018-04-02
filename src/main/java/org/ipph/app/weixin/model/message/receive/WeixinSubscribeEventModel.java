package org.ipph.app.weixin.model.message.receive;

import org.ipph.app.weixin.model.message.WeixinMessageModel;

public class WeixinSubscribeEventModel extends WeixinMessageModel{
	private String Event;

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
	
}
