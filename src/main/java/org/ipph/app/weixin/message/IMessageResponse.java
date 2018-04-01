package org.ipph.app.weixin.message;

import org.ipph.app.weixin.model.message.MessageModel;

public interface IMessageResponse {
	
	public String responseMessage(MessageModel message);
}
