package org.ipph.app.weixin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/app")
public class WeixinController {
	@RequestMapping("/index")
	@ResponseBody
    public String index(HttpServletRequest request,HttpServletResponse response,String echostr ){
		if(null!=echostr) {//校验数据
			String signature=request.getParameter("signature");
			String timestamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			if(WeixinValidUtil.valid(timestamp, nonce, signature)) {
				return echostr;
			}
		}else {
			
		}
		return null;
    }
	
	public String receiveMessage(InputStream in) {
		BufferedReader reader=null;
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			char[] buf=new char[1024];
			StringBuilder message=new StringBuilder();
			int len=0;
			while((len=reader.read(buf))!=-1) {
				message.append(new String(buf,0,len));
			}
			System.out.println(message);
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
}
