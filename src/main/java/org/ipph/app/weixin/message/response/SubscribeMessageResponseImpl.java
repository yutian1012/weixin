package org.ipph.app.weixin.message.response;

import org.ipph.app.weixin.model.message.EventMessageModel;
import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.model.message.TextMessageModel;
import org.ipph.app.weixin.util.WeixinMessageUtil;

public class SubscribeMessageResponseImpl implements IMessageResponse{

	@Override
	public String responseMessage(MessageModel message) {
		if(!(message instanceof EventMessageModel)) return null;
		
		TextMessageModel response=new TextMessageModel();
		
		response.setToUserName(message.getFromUserName());
		response.setFromUserName(message.getToUserName());
		response.setCreateTime(System.currentTimeMillis());
		response.setMsgType("text");
		
		//调用模板方法获取响应数据
		response.setContent(setTextMessageContent((TextMessageModel)message));
		
		try {
			return WeixinMessageUtil.bean2Xml(TextMessageModel.class, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 设置事件的响应消息文本
	 * @param message
	 * @return
	 */
	public String setTextMessageContent(TextMessageModel message) {
		StringBuilder result=new StringBuilder();
		result.append("欢迎关注专利扫一扫，通过输入申请号，即可查询专利的著录项信息！");
		return result.toString();
	}
}
