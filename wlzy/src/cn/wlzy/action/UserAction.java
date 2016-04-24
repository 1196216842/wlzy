package cn.wlzy.action;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
import cn.wlzy.service.MyFileService;
import cn.wlzy.service.NoticeService;
import cn.wlzy.service.ReplyService;
import cn.wlzy.service.TopicService;
import cn.wlzy.service.UserService;
import cn.wlzy.utils.Encrypt;
@Controller("userAction")
@Scope("prototype")
public class UserAction extends ActionSupport implements Serializable,ModelDriven<User> {
	private static final long serialVersionUID = 1L;
	private User model=new User();
	private int currentPage=1;
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public User getModel() {
		return model;
	}
	private String[] passwords;
	public String[] getPasswords() {
		return passwords;
	}
	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}
	
	@Resource(name="userService")
	private UserService userService;
	@Resource(name="topicService")
	private TopicService topicService;
	@Resource(name="replyService")
	private ReplyService replyService;
	@Resource(name="myFileService")
	private MyFileService myFileService;
	@Resource(name="noticeService")
	private NoticeService noticeService;

	
	
	/**
	 * 显示用户列表(用户管理)
	 * @return
	 */
	public String list(){
		PageRoll pageRoll=new PageRoll();
		pageRoll.setCurrentPage(currentPage);
		List<User> users=userService.findPageRoll(pageRoll);
		ActionContext.getContext().put("users", users);
		ActionContext.getContext().put("pageRoll", pageRoll);
		return "list";
	}
	/**
	 * 用户删除列表(用户管理)
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String delete(){
		//一般来说是不能够删除用户的，但是如果一定要删除呢，也可以,但是帖子和回复是不能够被删除的,建议不要这样
		User user=userService.findById(model.getId());
		List<Topic> topics=topicService.findByUser(user);
		List<Reply> replies=replyService.findByUser(user);
		List<MyFile> myFiles=myFileService.findFileByUser(user);
		List<Notice> notices=noticeService.findByUser(user);
		//回复关系
		for (Iterator iterator = replies.iterator(); iterator.hasNext();) {
			Reply reply = (Reply) iterator.next();
			reply.setAuthor(null);
			replyService.update(reply);
			
		}
		//主题关系
		for (Iterator iterator = topics.iterator(); iterator.hasNext();) {
			Topic topic = (Topic) iterator.next();
			topic.setAuthor(null);
			topicService.update(topic);
		}
		//文件关系
		for (Iterator iterator = myFiles.iterator(); iterator.hasNext();) {
			MyFile myFile = (MyFile) iterator.next();
			myFile.setUser(null);
			myFileService.update(myFile);
		}
		//通知关系:是老师或管理员才处理
		if(user.getUserType()==1||user.getUserType()==2){
			for (Iterator iterator = notices.iterator(); iterator.hasNext();) {
				Notice notice = (Notice) iterator.next();
				notice.setUser(null);
				noticeService.update(notice);
			}
		}
		//---------------------------------------------------
		
		userService.delete(model.getId());
		return "toList";
	}
	/**
	 * 用户添加页面(用户管理)
	 * @return
	 */
	public String addUI(){
		return "addUI";
	}
	/**
	 * 用户添加(用户管理)
	 * @return
	 */
	public String add(){
		//先判断用户是否已存在
		List<User> users=userService.findAll();
		for (int i = 0; i < users.size(); i++) {
		if(model.getUsername().equals(users.get(i).getUsername())){
			//用户已经存在
		   ActionContext.getContext().put("message", "用户名已存在");
		   return "addUI";
		}
					
	}
		String encryptPassword=new String(Encrypt.encrypt(model.getPassword().trim()));
		model.setPassword(encryptPassword);
		userService.save(model);
		return "toList";//注册成功
	}
	/**
	 * 用户修改页面(用户管理)
	 * @return
	 */
	public String editUI(){
		
		//准备回显数据
		User user=userService.findById(model.getId());
		ActionContext.getContext().put("user",user);
		return "editUI";
	}
	/**
	 * 用户修改(用户管理)
	 * @return
	 */
	public String edit(){
		//先判断用户是否已存在
		List<User> users=userService.findAll();
		User user=userService.findById(model.getId());
		if(!user.getUsername().equals(model.getUsername())){
				for (int i = 0; i < users.size(); i++) {
					if(model.getUsername().equals(users.get(i).getUsername())){
						//用户已经存在
						ActionContext.getContext().put("message", "用户名已存在");
						//准备回显数据
						ActionContext.getContext().put("user",user);
						return "editUI";
					}
				}
		}
		//获得修改的对象
		user.setUsername(model.getUsername());
		
		//如果密码变化需要加密(注意密码必须存在):这还是会产生问题，密码应该不能被修改，除非特意
/*		System.err.println(user.getPassword());
		System.err.println(model.getPassword());
		System.err.println(user.getPassword().equals(model.getPassword()));
		
		if(!user.getPassword().equals(model.getPassword())){
			String encryptPassword=new String(Encrypt.encrypt(model.getPassword().trim()));
			user.setPassword(encryptPassword);//需要加密
		}*/
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setEmail(model.getEmail());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setDescription(model.getDescription());
		user.setUserType(model.getUserType());//修改的时候类型不变，以后会专门弄个模块控制权限
		userService.update(user);
		return "toList";
	}
	
	/**
	 * 初始化密码123
	 * @return
	 */
	public String initialization(){
		User user=userService.findById(model.getId());
		//其他的不变，改变密码
		String password="123";
		String encryptPassword=new String(Encrypt.encrypt(password.trim()));
		user.setPassword(encryptPassword);
		userService.update(user);
		return "toList";
	}
	
	
	/**
	 * 个人信息(个人中心)
	 * @return
	 */
public String self(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		//准备数据
		User user=(User)session.getAttribute("user");
		PageRoll pageRoll=new PageRoll();
		pageRoll.setCurrentPage(currentPage);
		List<Topic> topics=topicService.findByUser(user,pageRoll);
		ActionContext.getContext().put("topics", topics);
		ActionContext.getContext().put("pageRoll", pageRoll);
		return "self";
	}
	/**
	 * 个人信息修改页面(个人中心)
	 */
	public String selfEditUI(){
		//准备回显数据:由于是放在session当中，所以根本不需要数据
		//User user=userService.findById(model.getId());
		//ActionContext.getContext().getValueStack().push(user);
		return "selfEditUI";
	}
	/**
	 * 个人信息修改(个人中心)
	 * @return
	 */
	public String selfEdit(){
		//获得修改的对象
		//先判断用户是否已存在
		List<User> users=userService.findAll();
		User user=userService.findById(model.getId());
		if(!user.getUsername().equals(model.getUsername())){
				for (int i = 0; i < users.size(); i++) {
					if(model.getUsername().equals(users.get(i).getUsername())){
						//用户已经存在
						ActionContext.getContext().put("message", "用户名已存在");
						//准备回显数据
						ActionContext.getContext().put("user",user);
						return "selfEditUI";
					}
				}
		}
		user.setUsername(model.getUsername());
		/*//如果密码变化需要加密：密码应该不要直接修改，可以初始化或自己单独修改
		if(!user.getPassword().equals(model.getPassword())){
				String encryptPassword=new String(Encrypt.encrypt(model.getPassword().trim()));
				user.setPassword(encryptPassword);//需要加密
		}*/
		user.setName(model.getName());
		user.setGender(model.getGender());
		user.setEmail(model.getEmail());
		user.setPhoneNumber(model.getPhoneNumber());
		user.setDescription(model.getDescription());
		user.setUserType(model.getUserType());//修改的时候类型不变，以后会专门弄个模块控制权限
		userService.update(user);
		//把上一session里面的清空
		ServletActionContext.getRequest().getSession().removeAttribute("user");
		//得重新把user放到session里去，不然看不到的
		ServletActionContext.getRequest().getSession().setAttribute("user", user);
		return "toSelf";
	}
	/**
	 * 注册(个人中心)
	 * @return
	 */
	public String registerUI(){
		return "registerUI";
	}
	public String register(){
		//先判断用户是否已存在
		List<User> users=userService.findAll();
		for (int i = 0; i < users.size(); i++) {
			if(model.getUsername().equals(users.get(i).getUsername())){
				//用户已经存在
				ActionContext.getContext().put("message", "用户名已存在");
				return "registerUI";
			}
			
		}
		String encryptPassword=new String(Encrypt.encrypt(model.getPassword().trim()));
		model.setPassword(encryptPassword);
		userService.save(model);
		return "tohome";//注册成功
	}
	
	/**
	 *登录
	 */
	public String loginUI(){
		return "loginUI";
	}
	public String login(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		String encryptPassword=new String(Encrypt.encrypt(model.getPassword().trim()));
		User user=userService.findByUsernameAndPasswordAndUserType(model.getUsername(),encryptPassword,model.getUserType());
		if(user!=null){
			session.setAttribute("user", user);
			return "homeList";
		}else{
			ActionContext.getContext().put("message", "用户名或密码不正确");
			return "loginUI";
		}
	}
	/**
	 *注销的话直接清空session就可以了
	 */
	public String cancel(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		//登录前先把session里面的东西清空
		session.removeAttribute("user");
		return "homeList";
	}
	
	//其他人中心
	public String other(){
		User user=userService.findById(model.getId());
		//准备数据
		PageRoll pageRoll=new PageRoll();
		pageRoll.setCurrentPage(currentPage);
		List<Topic> topics=topicService.findByUser(user,pageRoll);
		ActionContext.getContext().put("topics", topics);
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("pageRoll", pageRoll);
		return "other";
	}
	
	
	
	//添加朋友
	public String addFriend(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User)session.getAttribute("user");
		
		User user1=userService.findById(user.getId());
		User friend1=userService.findById(model.getId());
		//朋友是相对的，你加了我为朋友后，相当于我也加了你为朋友
		friend1.getUsers().add(user1);
		user1.getUsers().add(friend1);
		
		userService.update(friend1);
		userService.update(user1);
		
//		User user2=userService.findById(user.getId());
//		User friend2=userService.findById(model.getId());
//		//朋友是相对的，你加了我为朋友后，相当于我也加了你为朋友
//		user2.getUsers().add(friend2);
//		userService.update(user2);
		return "homeList";//以后再整合到一些页面
	}
/*	
	*//**
	 * self修改密码
	 * @return
	 */
	public String initPassword(){
		HttpSession session=ServletActionContext.getRequest().getSession();
		User userSession=(User) session.getAttribute("user");
		User user=userService.findById(userSession.getId());
		//其他的不变，改变密码
		String password="123";
		String encryptPassword=new String(Encrypt.encrypt(password.trim()));
		user.setPassword(encryptPassword);
		userService.update(user);
		return "toSelf";
	}
	public String changePasswordUI(){
		return "passwordUI";
	}
	public String changePassword(){
		User user=userService.findById(model.getId());
		String encryptPassword=new String(Encrypt.encrypt(passwords[0].trim()));
		if(encryptPassword.equals(user.getPassword())){//证明原密码正确
			user.setPassword(new String(Encrypt.encrypt(passwords[1].trim())));
			userService.update(user);
			return "toSelf";
		}else{
			ActionContext.getContext().put("message", "密码错误");
			return "passwordUI";
		}
		
	}
	
	//转到聊天的界面
	public String chat(){
		return "chat";
	}
	
}
