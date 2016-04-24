package cn.wlzy.domain;

import java.util.Date;
import java.util.Set;
public class Topic extends Article {
	/**普通帖*/
	public static final int TYPE_NORMAL=0;
	/**精华帖*/
	public static final int TYPE_BEST=1;
	/**置顶帖*/
	public static final int TYPE_TOP=2;
	private int type;//主题类型
	private int replyCount;//主题回复数目
	private Date lastUpdateTime;//主题最后更新时间
	private Set<Reply> replies;//主题与回复一对多
	private Forum forum;//主题与板块多对一
	private Reply lastReply;//主题与回复一对一，外键在这里
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	public Forum getForum() {
		return forum;
	}
	public void setForum(Forum forum) {
		this.forum = forum;
	}
	public Reply getLastReply() {
		return lastReply;
	}
	public void setLastReply(Reply lastReply) {
		this.lastReply = lastReply;
	}
	
}
