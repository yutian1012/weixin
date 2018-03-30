package org.ipph.app.weixin.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.DocumentException;
import org.ipph.app.weixin.model.MessageModel;
import org.ipph.app.weixin.xml.MessageHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class WeixinMessageUtil {
	/**
	 * 获取从流中对象
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	public static MessageModel getMessage(InputStream in) throws IOException, DocumentException, ParserConfigurationException, SAXException{
		// 创建解析工厂  
        SAXParserFactory factory = SAXParserFactory.newInstance();  
        // 创建解析器  
        SAXParser parser = factory.newSAXParser();
        // 得到读取器  
        XMLReader reader = parser.getXMLReader();
        //设置内容处理器
        MessageHandler handler=new MessageHandler();
        reader.setContentHandler(handler);
        //读取xml文档
        reader.parse(new InputSource(in));
        
        return handler.getMessage();
	}
	/**
	 * xml字符串转成对应的对象
	 * @param xml
	 * @throws Exception
	 */
	public static void xml2Bean(String xml)throws Exception{
        
        JAXBContext context = JAXBContext.newInstance(MessageModel.class);// 获取上下文对象  
        
        Unmarshaller unmarshaller=context.createUnmarshaller();//根据上下文获取Unmarshaller对象
          
        MessageModel message=(MessageModel)unmarshaller.unmarshal(new StringReader(xml));
        
        System.out.println(message);
    }
    
    /**
     * 将对象转换成xml字符串
     * @throws Exception
     */
    public static String bean2Xml(MessageModel message)throws Exception{
        JAXBContext context = JAXBContext.newInstance(MessageModel.class);// 获取上下文对象  
        // 根据上下文获取marshaller对象
        Marshaller marshaller = context.createMarshaller();   
        // 设置编码字符集  
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
        // 格式化XML输出，有分行和缩进
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);   
          
        //marshaller.marshal(getSimpleDepartment(),System.out);// 打印到控制台  
          
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        marshaller.marshal(message, baos);  
        return new String(baos.toByteArray()); // 生成XML字符串  
    }

}
