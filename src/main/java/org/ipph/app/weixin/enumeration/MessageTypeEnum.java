package org.ipph.app.weixin.enumeration;

public enum MessageTypeEnum {
	TEXT("text"),IMAGE("image"),VOICE("voice"),VIDEO("video"),SHORTVIDEO("shortVideo"),LINK("link"),
	EVENT("event");
	
	private String name;
	private MessageTypeEnum(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
}
