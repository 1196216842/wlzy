package cn.wlzy.domain;

import java.util.Date;

/**
 * 将教务公告单独拿出来当做一张表，只和用户多对一，只能老师才能发
 * @author Administrator
 *
 */
public class Notice {
	/**普通*/
	public static final int NORMAL=0;
	/**重点*/
	public static final int STRESS=1;
	/**紧急*/
	public static final int URGENCY=2;
	private Long id;//ID
	private String title;//标题
	private String content;//内容
	private Date postTime;//发布时间
	private int type;//公告类别
	private User user;//用户：限定为老师才有这个权限
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostTime() {
		return postTime;
	}
	public void setPostTime(Date postTime) {
		this.postTime = postTime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Notice [id=" + id + ", title=" + title + ", content=" + content
				+ ", postTime=" + postTime + ", type=" + type + ", user="
				+ user + "]";
	}
	
}
