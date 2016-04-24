package cn.wlzy.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Topic;
import cn.wlzy.service.ForumService;
import cn.wlzy.service.TopicService;
@Controller("forumAction")
@Scope("prototype")
public class ForumAction extends ActionSupport implements Serializable,
		ModelDriven<Forum> {
	private static final long serialVersionUID = 1L;
	
	private Forum model=new Forum();
	private int currentPage=1;
	//private int pageSize=5;
	
	
//	public int getPageSize() {
//		return pageSize;
//	}
//
//	public void setPageSize(int pageSize) {
//		this.pageSize = pageSize;
//	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Resource(name="forumService")
	private ForumService forumService;
	@Resource(name="topicService")
	private TopicService topicService;
	
	public Forum getModel() {
		return model;
	}
	
	//显示版块列表
	public String list(){
		List<Forum> forums=forumService.findAll();
		ActionContext.getContext().put("forums", forums);
		ActionContext.getContext().put("size", forums.size());
		
		//获取热门帖子ddd
		List<Topic> topics=topicService.findHotTopics();
		ActionContext.getContext().put("topics", topics);
		return "list";
	}
	
	
	//显示单个版块里面的主题列表
	/*public String show(){
		//准备数据:Forum
		Forum forum=forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		//准备数据：Topic
	    List<Topic> topics=topicService.findByForum(forum);
		ActionContext.getContext().put("topics", topics);
		return "show";
	}*/
	
	/**
	 * 根据forum和第几页查询
	 */
	public String show(){
		//准备数据:Forum
		Forum forum=forumService.findById(model.getId());
		ActionContext.getContext().put("forum", forum);
		//准备数据：Topic
		PageRoll pageRoll=new PageRoll();
		pageRoll.setCurrentPage(currentPage);
		pageRoll.setPageSize(14);
	    List<Topic> topics=topicService.findByForum(forum,pageRoll);
		ActionContext.getContext().put("topics", topics);
		ActionContext.getContext().put("pageRoll", pageRoll);
		return "show";
	}
	//显示主页
	public String index(){
		return "index";
	}
}
