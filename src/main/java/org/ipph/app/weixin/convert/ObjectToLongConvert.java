package org.ipph.app.weixin.convert;

import org.apache.commons.logging.LogFactory;  

public class ObjectToLongConvert implements IConvert<Object,Long> {  
  
    @Override  
    public Long convert(Object source) {  
        if(source == null) return null;  
        try {  
            return Long.parseLong(source.toString().trim());  
        } catch (NumberFormatException e) {  
            LogFactory.getLog(getClass()).info("ObjectToLongConvert failed: "+source, e);  
            return null;  
        }  
    }  
  
} 
