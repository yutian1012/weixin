package org.ipph.app.weixin.convert;

import org.apache.commons.logging.LogFactory;  

public class ObjectArrToIntArrConvert implements IConvert<Object[],int[]> {  
  
    @Override  
    public int[] convert(Object[] source) {  
        if(source == null) return null;  
        int[] res = new int[source.length];  
        for(int i=0;i<source.length;i++){  
            try {  
                res[i] = Integer.parseInt(source[i].toString());  
            } catch (NumberFormatException e) {  
                LogFactory.getLog(getClass()).info("ObjectArrToIntArrConvert failed, bad value: "+source[i].toString(), e);  
                return null;  
            }  
        }  
        return res;  
    }  
  
}  