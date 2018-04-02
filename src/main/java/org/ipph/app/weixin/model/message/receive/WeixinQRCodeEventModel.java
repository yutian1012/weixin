package org.ipph.app.weixin.model.message.receive;

import org.ipph.app.weixin.model.message.WeixinMessageModel;

public class WeixinQRCodeEventModel extends WeixinMessageModel{
	private String Event;
	private String EventKey;
	private String Ticket;
	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public String getTicket() {
		return Ticket;
	}
	public void setTicket(String ticket) {
		Ticket = ticket;
	}
}
