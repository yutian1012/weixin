package org.ipph.app.weixin.xml;

public class XmlElement {
	//公用字段
	public static final String ToUserName="ToUserName";
	public static final String FromUserName="FromUserName";
	public static final String CreateTime="CreateTime";
	public static final String MsgType="MsgType";
	
	//消息中存在，事件中不存在
	public static final String MsgId="MsgId";
	//媒体公用字段,媒体id，可以调用多媒体文件下载接口拉取数据
	public static final String MediaId="MediaId";
	//文本消息字段
	public static final String Content="Content";
	//图片
	public static final String PicUrl="PicUrl";
	//语音
	public static final String Format="Format";
	//开头语言识别后，语言识别的文字信息
	public static final String Recognition="Recognition";
	//视频,视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据
	public static final String ThumbMediaId="ThumbMediaId";
	//连接
	public static final String Title="Title";
	//连接描述
	public static final String Description="Description";
	//连接URL
	public static final String Url="Url";
	//位置X
	public static final String Location_X="Location_X";
	//位置Y
	public static final String Location_Y="Location_Y";
	//缩放比例
	public static final String Scale="Scale";
	//位置信息
	public static final String Label="Label";
	
	//事件公用标签
	public static final String Event="Event";
	//上报地理位置事件,纬度
	public static final String Latitude="Latitude";
	//上报地理位置事件,经度
	public static final String Longitude="Longitude";
	//地理位置精度
	public static final String Precision="Precision";
	
	//自定义菜单接口中KEY值
	public static final String EventKey="EventKey";
	//二维码扫描
	public static final String Ticket="Ticket";
}
