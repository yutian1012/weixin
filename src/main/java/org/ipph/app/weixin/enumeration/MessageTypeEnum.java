package org.ipph.app.weixin.enumeration;

import org.ipph.app.weixin.model.message.receive.WeixinImageMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinLinkMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinTextMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinVideoMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinVoiceMessageModel;

public enum MessageTypeEnum {
	TEXT("text") {
		@Override
		public Class<?> getMessageModel() {
			return WeixinTextMessageModel.class;
		}
	},IMAGE("image") {
		@Override
		public Class<?> getMessageModel() {
			// TODO Auto-generated method stub
			return WeixinImageMessageModel.class;
		}
	},VOICE("voice") {
		@Override
		public Class<?> getMessageModel() {
			// TODO Auto-generated method stub
			return WeixinVoiceMessageModel.class;
		}
	},VIDEO("video") {
		@Override
		public Class<?> getMessageModel() {
			// TODO Auto-generated method stub
			return WeixinVideoMessageModel.class;
		}
	},SHORTVIDEO("shortVideo") {
		@Override
		public Class<?> getMessageModel() {
			// TODO Auto-generated method stub
			return WeixinVoiceMessageModel.class;
		}
	},LINK("link") {
		@Override
		public Class<?> getMessageModel() {
			// TODO Auto-generated method stub
			return WeixinLinkMessageModel.class;
		}
	},
	EVENT("event") {
		@Override
		public Class<?> getMessageModel() {
			return null;
		}
	};
	
	private String name;
	private MessageTypeEnum(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public abstract Class<?> getMessageModel();
}
