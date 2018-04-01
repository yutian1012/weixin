package org.ipph.app.weixin.model.message;

import javax.xml.bind.annotation.XmlElement;

public class EventMessageModel extends MessageModel{
	//用于事件
	private String Event;
	
	public String getEvent() {
		return Event;
	}
	@XmlElement(name="Event")
	public void setEvent(String event) {
		Event = event;
	}
}
