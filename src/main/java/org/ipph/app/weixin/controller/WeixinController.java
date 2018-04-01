package org.ipph.app.weixin.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ipph.app.weixin.message.WeixinMessageContext;
import org.ipph.app.weixin.model.message.MessageModel;
import org.ipph.app.weixin.util.WeixinMessageUtil;
import org.ipph.app.weixin.util.WeixinValidUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/app")
public class WeixinController {
	
	@Resource
	private WeixinMessageContext weixinMessageContext;
	
	@RequestMapping(value="/index")
	@ResponseBody
    public ResponseEntity<String> ResponseEntity(HttpServletRequest request,HttpServletResponse response) throws IOException{
		response.setCharacterEncoding("UTF-8");
		HttpHeaders headers = new HttpHeaders();
        MediaType mediaType = new MediaType("text","html",Charset.forName("utf-8"));
        headers.setContentType(mediaType);
		
		return new ResponseEntity<String>(getResponseText(request),headers,HttpStatus.OK);
    }
	/**
	 * 处理返回文档信息
	 * @param request
	 * @return
	 */
	private String getResponseText(HttpServletRequest request) {
		
		String result=null;
		String echostr =request.getParameter("echostr");
		if(null!=echostr) {//校验数据
			String signature=request.getParameter("signature");
			String timestamp=request.getParameter("timestamp");
			String nonce=request.getParameter("nonce");
			if(WeixinValidUtil.valid(timestamp, nonce, signature)) {
				result=echostr;
			}
		}else {
			InputStream in=null;
			try {
				in=request.getInputStream();
				MessageModel message=WeixinMessageUtil.getMessage(MessageModel.class, in);
				if(null!=message) {
					return weixinMessageContext.responseMessage(message);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(null!=in) {
					try {
						in.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
}
