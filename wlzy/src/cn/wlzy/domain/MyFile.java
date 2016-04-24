package cn.wlzy.domain;

import java.util.Date;

public class MyFile {
	
	public final static int NOTICE=0;//通知，虽然不属于文件，但这样更好
	public final static int VIDEO=1;//视频，点击播放，下载
	public final static int DOC=2;//文档，点击下载（在线打开）
	public final static int AUDIO=3;//音频，点击播放，下载
	public final static int IMG=4;//图片,显示，下载
	/**
	 * 需要再加三类
	 * 1：其他资料，只提供上传下载功能，不限定类型，所以定义为OTHER
	 * 2:物理学家，只能是word,或者为pdf共用DOC，不过多了一个类型，AUTHOR
	 * 3：物理实验，也只能是word,或pdf，共用DOC，不过多了一个类型，EXP；
	 * 页面查询也是全部查询出来
	 * 
	 */
	public final static int OTHER=5;//上传和下载功能
	public final static int AUTHOR=6;//上传和浏览
	public final static int EXP=7;//上传和浏览
	/*****************************************************/
	private Long id;//ID
	private String title;//上传的标题
	private String trueName;//文件实际名
	private String uuidName;//uuid名包括后缀，用来唯一定位，放在数据库中，可以定位下载
	private int fileType;//视频，文档，图片，音频
	private Date uploadTime;//上传时间,因为要根据时间来排序
	private User user;//文件与用户多对一
	/*private boolean flag;//判断转码后的文件是否存在，存在为true,不存在则为false
	
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}*/
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTrueName() {
		return trueName;
	}
	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}
	public String getUuidName() {
		return uuidName;
	}
	public void setUuidName(String uuidName) {
		this.uuidName = uuidName;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
