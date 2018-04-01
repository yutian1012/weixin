package org.ipph.app.weixin.patent;

import java.util.HashMap;
import java.util.Map;

import org.ipph.app.weixin.model.patent.PatentModel;
import org.ipph.app.weixin.model.patent.PatentResponseModel;
import org.ipph.app.weixin.util.EncrytUtil;
import org.ipph.app.weixin.util.WebUtil;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class PatentSearchContext {
	
	private static final String PATENT_SECRET="com.xxyy.zhuanlitong";
	
	private static final String PATENT_URL="http://api.souips.com:8080/ipms/pat";
	
	/**
	 * 获取专利
	 * @param appNumber
	 * @return
	 */
	public PatentModel getPatent(String appNumber) {
		Map<String,String> params=new HashMap<String, String>();
    	params.put("displayCols", "appNumber,appDate");
    	params.put("dbs", "fmzl");
    	params.put("exp","申请号=('"+appNumber+"')");
    	params.put("from", "0");
    	params.put("to","1");
    	params.put("order","");
    	params.putAll(getEencrytParam());
    	
    	
    	String result=WebUtil.doPost(PATENT_URL, params);
    	
    	if(null==result||"".equals(result)) return null;
    	
    	PatentModel patent=null;
    	
    	Gson gson=new Gson();
    	PatentResponseModel response=gson.fromJson(result, PatentResponseModel.class);
    	if(null!=response&&"SUCCESS".equals(response.getMessage())) {
    		if(null!=response.getResults()&&response.getResults().size()>0) {
    			patent=response.getResults().get(0);
    		}
    	}
    	
    	return patent;
	}
	
	/**
	 * 获取专利接口的加密串
	 */
	private Map<String,String> getEencrytParam(){
		Map<String,String> params=new HashMap<>();
		
		long time=System.currentTimeMillis();
    	params.put("keyStr", time+"");
    	params.put("valueStr", EncrytUtil.encrytMd5(time+PATENT_SECRET));
    	
    	return params;
	}
	
	/*public static void main(String[] args) {
		PatentSearchContext context=new PatentSearchContext();
		PatentModel patent=context.getPatent("CN201210105520.X");
		System.out.println(patent);
	}*/
   
}
