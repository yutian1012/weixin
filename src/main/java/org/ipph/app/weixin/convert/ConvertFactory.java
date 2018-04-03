package org.ipph.app.weixin.convert;

import java.util.Date;  
import java.util.HashMap;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;  
  
import org.apache.commons.logging.Log;  
import org.apache.commons.logging.LogFactory;  
  
public class ConvertFactory {  
      
    private static Log log = LogFactory.getLog(ConvertFactory.class);  
  
    public static Map<String,IConvert<?,?>> convertHandlers = new HashMap<String, IConvert<?,?>>();  
      
    /** 
     * 注册转换器 
     */  
    static{  
        convertHandlers.put(String[].class.getName()+"To"+int[].class.getName(), new ObjectArrToIntArrConvert());  
          
        convertHandlers.put(String.class.getName()+"To"+Date.class.getName(), new ObjectToDateConvert());
  
        convertHandlers.put(String.class.getName()+"To"+Double.class.getName(), new ObjectToFloatConvert());  
        convertHandlers.put(String.class.getName()+"To"+double.class.getName(), new ObjectToFloatConvert());  
          
        convertHandlers.put(String.class.getName()+"To"+Float.class.getName(), new ObjectToFloatConvert());  
        convertHandlers.put(String.class.getName()+"To"+float.class.getName(), new ObjectToFloatConvert());  
          
        convertHandlers.put(String.class.getName()+"To"+Integer.class.getName(), new ObjectToIntegerConvert());  
        convertHandlers.put(String.class.getName()+"To"+int.class.getName(), new ObjectToIntegerConvert());  
        
        convertHandlers.put(String.class.getName()+"To"+Long.class.getName(), new ObjectToLongConvert());  
        convertHandlers.put(String.class.getName()+"To"+long.class.getName(), new ObjectToLongConvert());  
          
        Set<Entry<String, IConvert<?,?>>> entites = convertHandlers.entrySet();  
        
        if(log.isDebugEnabled()) {
        	StringBuilder b = new StringBuilder();  
        	b.append("{");  
        	for(Entry<String, IConvert<?,?>> entry: entites){  
        		b.append(entry.getKey()).append("=").append(entry.getValue()).append(",");  
        	}  
        	b.append("}");  
        	log.debug("all support convert type: "+b.toString().replaceFirst(",}", "}"));  
        }
    }  
      
    @SuppressWarnings({ "unchecked", "rawtypes" })  
    public static <T> T convert(Class<T> clazz,Object val){  
        IConvert cv = convertHandlers.get(val.getClass().getName()+"To"+clazz.getName());
        if(cv == null){  
            log.info(clazz.getName()+"To"+val.getClass().getName()+ " convert failed: unsupport type");  
            return null;  
        }  
        return (T)cv.convert(val);  
    }  
} 
