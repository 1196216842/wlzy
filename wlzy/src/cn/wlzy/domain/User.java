package cn.wlzy.domain;

import java.io.Serializable;
import java.util.Set;
/**
 * 用户名，用来登录系统，一般为学生，老师，老师为管理员
 * @author Administrator
 *
 */
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	/**学生*/
	public static final int STUDENT=0;
	/**老师*/
	public static final int TEACHER=1;
	/**管理员*/
	public static final int ADMIN=2;
	
	//一般人注册进去都为学生，只不过还有一个超级管理员可以给这些分配权限，
	//比如晋升为老师，晋升为管理员，超级管理员是一开始就有的
	private Long id;//ID
	private int userType;
	private String username;//用户名,学生学号
	private String password;//密码，身份证后6位
	private String name;//真实姓名
	private String gender;//性别
	private String phoneNumber;//电话号码
	private String email;//邮箱
	private Set<User> friends;//我的朋友，用户与朋友一对多
	private Set<User> users;//朋友与用户多对一
	private String description;//个人简介
	private Set<Topic> topics;//用户和主题一对多
	private Set<Reply> replies;//用户和回复一对多
	private Set<MyFile> myFiles;//用户与文件一对多
	private Set<Notice> notices;//用户与公告一对多
	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Set<MyFile> getMyFiles() {
		return myFiles;
	}
	public void setMyFiles(Set<MyFile> myFiles) {
		this.myFiles = myFiles;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public Set<User> getFriends() {
		return friends;
	}
	public void setFriends(Set<User> friends) {
		this.friends = friends;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public Set<Reply> getReplies() {
		return replies;
	}
	public void setReplies(Set<Reply> replies) {
		this.replies = replies;
	}
	
	public Set<Notice> getNotices() {
		return notices;
	}
	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	//...
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", name=" + name + ", gender=" + gender
				+ ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", description=" + description + "]";
	}
	
}
