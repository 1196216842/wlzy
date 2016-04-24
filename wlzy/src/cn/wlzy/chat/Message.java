package cn.wlzy.chat;

import java.util.Set;

public class Message {
	private String come;//加入聊天发送的消息
	private String leave;//离开后发送的消息
	private Set<String> usernames;//用户列表
	private String content;//正式聊天发送的消息的内容
	public String getCome() {
		return come;
	}
	public void setCome(String come) {
		this.come = come;
	}
	public String getLeave() {
		return leave;
	}
	public void setLeave(String leave) {
		this.leave = leave;
	}
	public Set<String> getUsernames() {
		return usernames;
	}
	public void setUsernames(Set<String> usernames) {
		this.usernames = usernames;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
