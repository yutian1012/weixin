package org.ipph.app.weixin.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.DocumentException;
import org.ipph.app.weixin.model.message.MessageModel;
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
	
	public static <T> T getMessage(Class<T> clazz,InputStream in) {
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(in,Charset.forName("UTF-8")));
			char[] buf=new char[1024];
			StringBuilder message=new StringBuilder();
			int len=0;
			while((len=reader.read(buf))!=-1) {
				message.append(new String(buf,0,len));
			}
			if(message.length()>0) {
				return xml2Bean(clazz, message.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * xml字符串转成对应的对象
	 * @param xml
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xml2Bean(Class<T> clazz,String xml)throws Exception{
        
        JAXBContext context = JAXBContext.newInstance(clazz);// 获取上下文对象  
        
        Unmarshaller unmarshaller=context.createUnmarshaller();//根据上下文获取Unmarshaller对象
          
        T message=(T)unmarshaller.unmarshal(new StringReader(xml));
		//T message=(T)unmarshaller.unmarshal(new InputSource(xml));
        
        //System.out.println(message);
        return message;
    }
    
    /**
     * 将对象转换成xml字符串
     * @throws Exception
     */
    public static <T> String bean2Xml(Class<T> clazz,T bean)throws Exception{
        JAXBContext context = JAXBContext.newInstance(clazz);// 获取上下文对象  
        // 根据上下文获取marshaller对象
        Marshaller marshaller = context.createMarshaller();   
        // 设置编码字符集  
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
        // 格式化XML输出，有分行和缩进
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
          
        //marshaller.marshal(getSimpleDepartment(),System.out);// 打印到控制台  
          
        ByteArrayOutputStream baos = new ByteArrayOutputStream();  
        marshaller.marshal(bean, baos);  
        return new String(baos.toByteArray()); // 生成XML字符串  
    }
    
}
