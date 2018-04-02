package org.ipph.app.weixin.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.xml.MessageHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class WeixinMessageUtil {
	
	/**
	 * 将xml节点转换为Map集合
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(InputStream in) throws IOException, DocumentException{

		Map<String, String> map = new HashMap<String, String>();
		//从dom4j的jar包中，拿到SAXReader对象。
		SAXReader reader = new SAXReader();
		
		Document doc =  reader.read(in);//从reader对象中,读取输入流
		Element root = doc.getRootElement();//获取XML文档的根元素
		List<Element> list = root.elements();//获得根元素下的所有子节点
		for (Element e : list) {
			map.put(e.getName(), e.getText());//遍历list对象，并将结果保存到集合中
		}
		return map;
	}
	/**
	 * 通过xml获取map对象
	 * @param xml
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xmlToMap(String xml) throws IOException, DocumentException{

		Map<String, String> map = new HashMap<String, String>();
		//从dom4j的jar包中，拿到SAXReader对象。
		SAXReader reader = new SAXReader();
		
		Document doc =  reader.read(new StringReader(xml));
		Element root = doc.getRootElement();//获取XML文档的根元素
		List<Element> list = root.elements();//获得根元素下的所有子节点
		for (Element e : list) {
			map.put(e.getName(), e.getText());//遍历list对象，并将结果保存到集合中
		}
		return map;
	}
	/**
	 * 获取输入流输入的字符串
	 * @param clazz
	 * @param in
	 * @return
	 */
	public static String getMessageXml(InputStream in) {
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
				return message.toString();
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
	 * 获取从流中对象
	 * @param in
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 */
	@Deprecated
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
