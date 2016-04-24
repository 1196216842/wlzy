package cn.wlzy.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import cn.wlzy.domain.Forum;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.Topic;
import cn.wlzy.service.ForumService;
import cn.wlzy.service.MyFileService;
import cn.wlzy.service.NoticeService;
import cn.wlzy.service.TopicService;
/**
 * 首页，读取消息显示，相当于一个门户
 * @author Administrator
 *
 */
@Controller("homeAction")
@Scope("prototype")
public class HomeAction extends ActionSupport implements Serializable{
	private static final long serialVersionUID = 1L;
	private int size=10;
	private int type=MyFile.AUTHOR;
	/*private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}*/

	//@Resource(name="forumService")
	//private ForumService forumService;
	@Resource(name="topicService")
	private TopicService topicService;
	@Resource(name="noticeService")
	private NoticeService noticeService;
	@Resource(name="myFileService")
	private MyFileService myFileService;
	@Resource(name="forumService")
	private ForumService forumService;
	
	public String list(){
		//准备十条最新教务公告
		size=6;
		List<Notice> notices=noticeService.findNotice(size);
		//准备数据：最新文章列表，十条，只需要显示文章题目，文章回复数，文章发布日期，名字就将最新文章
		size=6;
	    List<Topic> topics=topicService.findNewTopics(size);
		//准备10条物理学家
	    size=6;
	    type=MyFile.AUTHOR;
	    List<MyFile> authors=myFileService.findNewMyFile(size,type);
	    //准备10条物理实验
	    size=6;
	    type=MyFile.EXP;
	    List<MyFile> exps=myFileService.findNewMyFile(size,type);
	    //准备论坛版块名称
	    List<Forum> forums=forumService.findAll();
	    
	    ActionContext.getContext().put("notices", notices);
	    ActionContext.getContext().put("topics", topics);
	    ActionContext.getContext().put("authors", authors);
	    ActionContext.getContext().put("exps", exps);
	    ActionContext.getContext().put("forums", forums);
		//准备联系人列表数据
		/*HttpSession session=ServletActionContext.getRequest().getSession();
		User user=(User)session.getAttribute("user");
		if(user!=null){
			User user1=userService.findById(user.getId());
			Set<User> friends=(Set<User>)user1.getFriends();
			ActionContext.getContext().put("friends", friends);
		}*/
		//ActionContext.getContext().put("message", message);
		return "list";
	}
	
	//转到聊天的界面
	public String chat(){
		return "chat";
	}
	
	public String top(){
		return "top";
	}
	public String bottom(){
		return "bottom";
	}
}
