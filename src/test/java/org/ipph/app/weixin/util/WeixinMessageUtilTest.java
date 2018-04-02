package org.ipph.app.weixin.util;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import org.ipph.app.weixin.message.receive.WeixinMessageReceiveContext;
import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.model.message.WeixinMessageModel;
import org.junit.Test;

public class WeixinMessageUtilTest {

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
	
	@Test
	public void testXml2() {
		//fail("Not yet implemented");
		String weixinMessage="<xml>" + 
				"<ToUserName>test</ToUserName>" + 
				"<FromUserName>xxxx</FromUserName>" + 
				"<CreateTime>1348831860</CreateTime>" + 
				"<MsgType>text</MsgType>" + 
				"<Content>ttttt</Content>" + 
				"<MsgId>1234567890123456</MsgId>\r\n" + 
				"</xml>";
		try {
			WeixinMessageReceiveContext context=new WeixinMessageReceiveContext();
			WeixinMessageModel message=context.getWeixinMessageModel(weixinMessage);
			
			assertNotNull(message);
			
			/*String result=WeixinMessageUtil.bean2Xml(MessageModel.class, message);
			
			System.out.println(result);
			
			assertNotNull(result);*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
