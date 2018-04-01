package org.ipph.app.weixin.xml;

import org.ipph.app.weixin.model.message.MessageModel;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MessageHandler extends DefaultHandler {
	private MessageModel message;
	
	private StringBuffer temp=new StringBuffer();
	
	public MessageModel getMessage(){
		return this.message;
	}
	
	@Override
    public void startDocument () {  
        //开始解析文档  
    	message=new MessageModel();
    }  
    
	/**
     * 获取标签体中的值
     */
    @Override  
    public void characters(char[] ch, int start, int length)throws SAXException {
    	if(length<=0) return;
    	temp.append(new String(ch,start,length));
    }  
    /**
     * 标签结束时赋值对象并重置数据
     */
    @Override  
    public void endElement(String uri, String localName, String qName)throws SAXException { 
    	//解析标签内的节点内容
    	if(XmlElement.ToUserName.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setToUserName(temp.toString());
    			}
    		}
    	}else if(XmlElement.FromUserName.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setFromUserName(temp.toString());
    			}
    		}
    	}else if(XmlElement.CreateTime.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setCreateTime(Long.parseLong(temp.toString()));
    			}
    		}
    	}else if(XmlElement.MsgType.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setMsgType(temp.toString());
    			}
    		}
    	}/*else if(XmlElement.Content.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setContent(temp.toString());
    			}
    		}
    	}else if(XmlElement.MsgId.equals(qName)){
    		if(null!=message){
    			if(temp.length()>0){
    				message.setMsgId(temp.toString());
    			}
    		}
    	}*/
    	
    	temp.setLength(0);
    }  
}
