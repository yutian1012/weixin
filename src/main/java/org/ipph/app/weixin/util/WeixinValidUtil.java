package org.ipph.app.weixin.util;

import java.util.Arrays;

import org.ipph.app.weixin.WeixinConstant;

public class WeixinValidUtil {
	
	/**
	 * 微信校验
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	public static boolean valid(String timestamp,String nonce,String signature) {
		String[] params=new String[] {WeixinConstant.TOKEN,timestamp,nonce};
		Arrays.sort(params);
		StringBuilder temp=new StringBuilder();
		for(String s:params) {
			temp.append(s);
		}
		String result=EncrytUtil.encrytSHA1(temp.toString());
		return result.equals(signature);
	}
	
}
