package org.ipph.app.weixin.util;

import static org.junit.Assert.*;

import org.ipph.app.weixin.model.message.MessageModel;
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

}
