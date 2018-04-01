package org.ipph.app.weixin.model.message;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class MessageModel {
	private String ToUserName;
	
	private String FromUserName;
	
	private Long CreateTime;
	
	private String MsgType;
	
	private String dealType;
	
	public String getToUserName() {
		return ToUserName;
	}
	@XmlElement(name="ToUserName")
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	@XmlElement(name="FromUserName")
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public Long getCreateTime() {
		return CreateTime;
	}
	@XmlElement(name="CreateTime")
	public void setCreateTime(Long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	@XmlElement(name="MsgType")
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public String getDealType() {
		return dealType;
	}
	@XmlElement(name="dealType",required=false)
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
}
