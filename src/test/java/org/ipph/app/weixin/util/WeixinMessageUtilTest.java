package org.ipph.app.weixin.util;

import static org.junit.Assert.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ipph.app.weixin.message.receive.WeixinMessageReceiveContext;
import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.model.message.WeixinMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinImageMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinLinkMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinLocationMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinTextMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinVideoMessageModel;
import org.ipph.app.weixin.model.message.receive.WeixinVoiceMessageModel;
import org.junit.Test;

public class WeixinMessageUtilTest {
	private WeixinMessageReceiveContext context=new WeixinMessageReceiveContext();
	@Test
	public void testXml() {
		//fail("Not yet implemented");
		String weixinMessage="<xml>" + 
				"<ToUserName>test</ToUserName>" + 
				"<FromUserName>xxxx</FromUserName>" + 
				"<CreateTime>1348831860</CreateTime>" + 
				"<MsgType>ttttt</MsgType>" + 
				"<Content>ttttt</Content>" + 
				"<MsgId>1234567890123456</MsgId>\r\n" + 
				"</xml>";
		try {
			MessageModel message=WeixinMessageUtil.xml2Bean(MessageModel.class,weixinMessage);
			
			assertNotNull(message);
			
			String result=WeixinMessageUtil.bean2Xml(MessageModel.class, message);
			
			System.out.println(result);
			
			assertNotNull(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 测试文本消息
	 */
	@Test
	public void testTextXml() {
		//fail("Not yet implemented");
		String weixinMessage="<xml>" + 
				"<ToUserName>test</ToUserName>" + 
				"<FromUserName>xxxx</FromUserName>" + 
				"<CreateTime>1348831860</CreateTime>" + 
				"<MsgType>text</MsgType>" + 
				"<Content>ttttt</Content>" + 
				"<MsgId>1234567890123456</MsgId>\r\n" + 
				"</xml>";
		assertEquals(WeixinTextMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	/**
		测试图片
	*/
	@Test
	public void testImageXml() {
		//fail("Not yet implemented");
		String weixinMessage="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[this is a url]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinImageMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	@Test
	public void testVoiceXml(){
		String weixinMessage="<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1357290913</CreateTime><MsgType>< ![CDATA[voice] ]></MsgType><MediaId>< ![CDATA[media_id] ]></MediaId><Format>< ![CDATA[Format] ]></Format><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinVoiceMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	@Test
	public void testVoice2Xml(){
		String weixinMessage="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1357290913</CreateTime><MsgType>< ![CDATA[voice] ]></MsgType><MediaId>< ![CDATA[media_id] ]></MediaId><Format>< ![CDATA[Format] ]></Format><Recognition>< ![CDATA[腾讯微信团队] ]></Recognition><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinVoiceMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	@Test
	public void testVideoXml(){
		String weixinMessage="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1357290913</CreateTime><MsgType>< ![CDATA[video] ]></MsgType><MediaId>< ![CDATA[media_id] ]></MediaId><ThumbMediaId>< ![CDATA[thumb_media_id] ]></ThumbMediaId><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinVideoMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	@Test
	public void testShortVideoXml(){
		String weixinMessage="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1357290913</CreateTime><MsgType>< ![CDATA[shortvideo] ]></MsgType><MediaId>< ![CDATA[media_id] ]></MediaId><ThumbMediaId>< ![CDATA[thumb_media_id] ]></ThumbMediaId><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinVideoMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	@Test
	public void testLocationXml(){
		String weixinMessage="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1351776360</CreateTime><MsgType>< ![CDATA[location] ]></MsgType><Location_X>23.134521</Location_X><Location_Y>113.358803</Location_Y><Scale>20</Scale><Label>< ![CDATA[位置信息] ]></Label><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinLocationMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	@Test
	public void testLinkXml(){
		String weixinMessage="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1351776360</CreateTime><MsgType>< ![CDATA[link] ]></MsgType><Title>< ![CDATA[公众平台官网链接] ]></Title><Description>< ![CDATA[公众平台官网链接] ]></Description><Url>< ![CDATA[url] ]></Url><MsgId>1234567890123456</MsgId></xml>";
		weixinMessage=weixinMessage.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", "");
		assertEquals(WeixinLinkMessageModel.class, getMessageModel(weixinMessage).getClass());
	}
	
	private WeixinMessageModel  getMessageModel(String weixinMessage){
		WeixinMessageModel message=null;
		try {
			
			message=context.getWeixinMessageModel(weixinMessage);
			
			assertNotNull(message);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
		
	}
	
	@Test
	public void test(){
		String test="<xml><ToUserName>< ![CDATA[toUser] ]></ToUserName><FromUserName>< ![CDATA[fromUser] ]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[this is a url]]></PicUrl><MediaId><![CDATA[media_id]]></MediaId><MsgId>1234567890123456</MsgId></xml>";
		
		
		//Pattern p=Pattern.compile("\\s(?=!\\[CDATA\\[toUser\\])");
		
		//Pattern p=Pattern.compile("\\s+(?=!\\[CDATA)");//向后查找
		Pattern p=Pattern.compile("(?<=\\])\\s+");
		Matcher m=p.matcher(test);
		if(m.find()){
			System.out.println(m.groupCount());
			System.out.println(m.group(0));
		}
		System.out.println(test.replaceAll("\\s+(?=!\\[CDATA)", "").replaceAll("(?<=\\])\\s+", ""));
	}

}
