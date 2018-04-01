package org.ipph.app.weixin.enumeration;

public enum EventTypeEnum {
	LOCATION("location"),SUBSCRIBE("subscribe"),UNSUBSCRIBE("unsubscribe"),CLICK("click"),VIEW("view"),SCAN("scan");
	
	private String name;
	private EventTypeEnum(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
}
