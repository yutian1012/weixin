package org.ipph.app.weixin.enumeration;

import org.ipph.app.weixin.model.message.receive.WeixinLocationEventModel;
import org.ipph.app.weixin.model.message.receive.WeixinMenuEventModel;
import org.ipph.app.weixin.model.message.receive.WeixinQRCodeEventModel;
import org.ipph.app.weixin.model.message.receive.WeixinSubscribeEventModel;

public enum EventTypeEnum {
	LOCATION("location") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			return WeixinLocationEventModel.class;
		}
	},SUBSCRIBE("subscribe") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			// TODO Auto-generated method stub
			if(null==eventKey) {
				return WeixinSubscribeEventModel.class;
			}else {
				return WeixinQRCodeEventModel.class;
			}
		}
	},UNSUBSCRIBE("unsubscribe") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			// TODO Auto-generated method stub
			return WeixinSubscribeEventModel.class;
		}
	},CLICK("click") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			// TODO Auto-generated method stub
			return WeixinMenuEventModel.class;
		}
	},VIEW("view") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			// TODO Auto-generated method stub
			return WeixinMenuEventModel.class;
		}
	},SCAN("scan") {
		@Override
		public Class<?> getEventModel(String eventKey) {
			// TODO Auto-generated method stub
			return  WeixinQRCodeEventModel.class;
		}
	};
	
	private String name;
	private EventTypeEnum(String name) {
		this.name=name;
	}
	public String getName() {
		return this.name;
	}
	public abstract Class<?> getEventModel(String eventKey);
}
