package org.ipph.app.weixin.message.response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.model.message.TextMessageModel;
import org.ipph.app.weixin.model.patent.PatentModel;
import org.ipph.app.weixin.patent.PatentSearchContext;
import org.ipph.app.weixin.util.PatentValidationUtil;
import org.ipph.app.weixin.util.WeixinMessageUtil;

public class TextMessageResponseImpl implements IMessageResponse{
	
	private PatentSearchContext PatentSearchContext=new PatentSearchContext();
	private DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");

	@Override
	public String responseMessage(MessageModel message) {
		if(!(message instanceof TextMessageModel)) return null;
		
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
	 * 设置响应内容
	 * @param message
	 * @return
	 */
	public String setTextMessageContent(TextMessageModel message) {
		String content=message.getContent();
		StringBuilder result=new StringBuilder();
		if(PatentValidationUtil.isValidAppNumber(content)) {
			PatentModel patent=PatentSearchContext.getPatent(content);
			if(null!=patent) {
				result.append("申请号：").append(patent.getAppNumber()).append("\r\n");
				result.append("申请日：").append(dateFormat.format(patent.getAppDate())).append("\r\n");
			}
		}
		if(result.length()==0) {
			//result.append("请输入正确的申请号，如：CN201210105520.X");
			result.append("您输入的数据：").append(content);
		}
		return result.toString();
	}

	
}
