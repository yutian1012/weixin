package org.ipph.app.weixin.model.message.receive;

import org.ipph.app.weixin.model.message.WeixinMessageModel;

public class WeixinVideoMessageModel extends WeixinMessageModel{
	private String MediaId;
	private String ThumbMediaId;
	private int MsgId;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getThumbMediaId() {
		return ThumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}
	public int getMsgId() {
		return MsgId;
	}
	public void setMsgId(int msgId) {
		MsgId = msgId;
	}
	
}
