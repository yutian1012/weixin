package org.ipph.app.weixin.model.message;

import javax.xml.bind.annotation.XmlElement;

public class TextMessageModel extends MessageModel{
	//用于文本消息
	private String Content;
		
	private String MsgId;
	
	public String getContent() {
		return Content;
	}
	@XmlElement(name="Content")
	public void setContent(String content) {
		Content = content;
	}
	public String getMsgId() {
		return MsgId;
	}
	@XmlElement(name="MsgId")
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
}
