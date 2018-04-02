package org.ipph.app.weixin.model.message.receive;

import org.ipph.app.weixin.model.message.WeixinMessageModel;

public class WeixinTextMessageModel extends WeixinMessageModel{
	private String Content;
	
	private int MsgId;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public int getMsgId() {
		return MsgId;
	}

	public void setMsgId(int msgId) {
		MsgId = msgId;
	}
	
}
