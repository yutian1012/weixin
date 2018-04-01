package org.ipph.app.weixin.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public class WebUtil {
	/**
	 * 发送post请求
	 */
	public static String doPost(String posturl,Map<String,String> params) {
		URLConnection conn=null;
		PrintWriter pw=null;
		BufferedReader reader = null;
		StringBuffer resultBuffer = new StringBuffer();
        try {
        	StringBuilder content=new StringBuilder();
        	for(String key:params.keySet()) {
        		content.append(key).append("=").append(URLEncoder.encode(params.get(key),"UTF-8")).append("&");
        	}
            //1.通过在 URL 上调用 openConnection 方法创建连接对象
            URL url=new URL(posturl);
            conn=url.openConnection();

            conn.setDoInput (true);  //默认为true 所以不设置也可以
            conn.setDoOutput(true);  //默认为false 发送post请求必须设置setDoOutput(true)
            conn.setUseCaches(false); //是否可以使用缓存 不使用缓存
            conn.setConnectTimeout(5000);//请求超时时间

            //2.2请求属性 
            //设置通用的请求属性 消息报头 即设置头字段 更多的头字段信息可以查阅HTTP协议
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");

            //2.3设置请求正文 即要提交的数据
            pw=new PrintWriter(new OutputStreamWriter(conn.getOutputStream()));
            pw.print(content.substring(0,content.length()-1));
            pw.flush();

            //3.使用 connect 方法建立到远程对象的实际连接。 
            conn.connect();

            //4.获取响应内容
            String tempLine = null;

            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while ((tempLine = reader.readLine()) != null) {
                resultBuffer.append(tempLine);
            }
            
        } catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
        	if(null!=pw) {
        		pw.close();
        	}
        	if(null!=reader) {
        		try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        return resultBuffer.toString();

    }
}
