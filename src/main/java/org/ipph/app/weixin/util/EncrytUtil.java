package org.ipph.app.weixin.util;

import java.security.MessageDigest;

public class EncrytUtil {
	
	private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5',  
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
	
	public static String encrytMd5(String text) {
		
		MessageDigest md5 = null;
        byte[] byteArray = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byteArray = text.getBytes("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
	
	/**
	 * 加密
	 * @param str
	 * @return
	 */
	public static String encrytSHA1(String str) {  
		if (str == null) {  
			return null;
		}  
		try {  
			MessageDigest messageDigest = MessageDigest.getInstance("SHA1");  
			messageDigest.update(str.getBytes());  
			return getFormattedText(messageDigest.digest());  
		} catch (Exception e) {  
			throw new RuntimeException(e);  
		}  
	}
	
	/** 
	* Takes the raw bytes from the digest and formats them correct. 
	* 
	* @param bytes the raw bytes from the digest. 
	* @return the formatted bytes. 
	*/  
	private static String getFormattedText(byte[] bytes) {  
		int len = bytes.length;  
		StringBuilder buf = new StringBuilder(len * 2);  
		// 把密文转换成十六进制的字符串形式  
		for (int j = 0; j < len; j++) {  
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);  
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);  
		}  
		return buf.toString();  
	}
}
