package cn.wlzy.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
import cn.wlzy.service.ForumService;
import cn.wlzy.service.ReplyService;
import cn.wlzy.service.TopicService;
@Controller("topicAction")
@Scope("prototype")
public class TopicAction extends ActionSupport implements Serializable,
		ModelDriven<Topic> {
	private static final long serialVersionUID = 1L;
	private Topic model=new Topic();
	private int currentPage=1;
	/**
	 *提供getSet方法获得JSON
	 * 
	 */
	/*private Map<Long,String> map=new HashMap<Long,String>();
	public Map<Long, String> getMap() {
		return map;
	}*/
/*	private List<Forum> forums;
	public List<Forum> getForums() {
		return forums;
	}
	public void setForums(List<Forum> forums) {
		this.forums = forums;
	}*/
/*	private List<Forum_JSON> forumJs=new ArrayList<Forum_JSON>();
	public void setForums(List<Forum_JSON> forumJs) {
		this.forumJs = forumJs;
	}
	public List<Forum_JSON> getForumJs() {
		return forumJs;
	}*/
	
	//--------------------------------------------------
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public Topic getModel() {
		return model;
	}
	@Resource(name="forumService")
	private ForumService forumService;
	@Resource(name="topicService")
	private TopicService topicService;
	@Resource(name="replyService")
	private ReplyService replyService;
	private Long forumId;

	//显示单个主题（主贴加回帖）
	/*public String show(){
		//准备数据：topic
		Topic topic=topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		//准备数据:replyList
		List<Reply> replys=replyService.findByTopic(topic);
		ActionContext.getContext().put("replys", replys);
		return "show";
	}*/
	public String show(){
		//准备数据:Forum
		Topic topic=topicService.findById(model.getId());
		ActionContext.getContext().put("topic", topic);
		//准备数据：Topic
		PageRoll pageRoll=new PageRoll();
		pageRoll.setCurrentPage(currentPage);
		pageRoll.setPageSize(5);
		List<Reply> replys=replyService.findByTopic(topic,pageRoll);
		
		ActionContext.getContext().put("replys", replys);
		ActionContext.getContext().put("pageRoll", pageRoll);
		//移动到其他版块
		List<Forum> forums=forumService.findAll();
		ActionContext.getContext().put("forums", forums);
		return "show";
		
	}
	//添加主题页面
/*	public String addUI(){
		//准备数据
		Forum forum= forumService.findById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}*/
	//设为精华帖
	public String hot(){
		Topic topic=topicService.findById(model.getId());
		topic.setType(1);
		topicService.update(topic);
		return "toShow";
	}
	//设为置顶
	public String top(){
		Topic topic=topicService.findById(model.getId());
		topic.setType(2);
		topicService.update(topic);
		return "toShow";
	}
	//设为普通
	public String comm(){
		Topic topic=topicService.findById(model.getId());
		topic.setType(0);
		topicService.update(topic);
		return "toShow";
	}
	//添加成功
	public String add(){
		//封装数据
		model.setForum(forumService.findById(forumId));
		//>>当前直接获取的信息
		model.setAuthor((User)ActionContext.getContext().getSession().get("user"));
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		topicService.save(model);
		
		return "toForumShow";//转到主题的列表页面
	}
/*	//移动到其他版块:用ajax技术
	public String moveUI(){
		//准备数据：帖子主题和对应版块的名字
		Topic topic=topicService.findById(model.getId());
//		this.setTopic(topic);
		//model=topic;
		ActionContext.getContext().put("topic", topic);
		//所有版块：forumList
		List<Forum> forums=forumService.findAll();
		for (Forum forum : forums) {
			Forum_JSON forumJ=new Forum_JSON();
			forumJ.setId(forum.getId());
			forumJ.setName(forum.getName());
			forumJs.add(forumJ);
		}
		ActionContext.getContext().put("forums", forums);
		return "moveUI";
	}*/
	//移动到其他版块
	public String move(){
		//封装数据
		Topic topic=topicService.findById(model.getId());
		int count=topic.getReplyCount();//所含回复数
		//先前的主题维护
		Forum beforeForum=topic.getForum();
		beforeForum.setTopicCount(beforeForum.getTopicCount()-1);
		beforeForum.setArticleCount(beforeForum.getArticleCount()-1-count);
		forumService.update(beforeForum);
		//移到的目标主题维护
		Forum forum=forumService.findById(forumId);
		forum.setTopicCount(forum.getTopicCount()+1);//版块主题数量加1
		forum.setArticleCount(forum.getArticleCount()+1+count);//帖子数量（主题+回复）
		topic.setForum(forum);
		topicService.update(topic);
		return "toForumShow";
	}
	//删除主题
	public String delete(){
		Topic topic=topicService.findById(model.getId());
		//维护特殊属性
		int count =topic.getReplyCount();//回复的数量
		Forum forum=topic.getForum();
		forum.setTopicCount(forum.getTopicCount()-1);
		forum.setArticleCount(forum.getArticleCount()-1-count);
		//把forumId的值设好
		forumId=forum.getId();
		//如果所删除的主题是最后发表的，那么，，先解除关系,需要把版块对应的最后主题维护为最后时间发表的主题
		//1:找到最后的帖子
		Topic lastTopic=forum.getLastTopic();
		//2:如果要删除的帖子与最后的帖子相同，则先解除关系
		if(topic.equals(lastTopic)){
			forum.setLastTopic(null);
			//3:删除topic
			topicService.delete(model.getId());
			//找到删除后本版最后的主题,设置为lastTopic
			lastTopic=topicService.findLastTopic(forum);
			forum.setLastTopic(lastTopic);
		}else{
			topicService.delete(model.getId());
		}
		//删除后再更新，不然会报错
		forumService.update(forum);
		return "toForumShow";
	}
	public String editUI(){
		//准备数据
		Topic topic=topicService.findById(model.getId());
		Forum forum=topic.getForum();
		ActionContext.getContext().put("forum", forum);
		ActionContext.getContext().put("topic",topic);
		return "editUI";
	}
	public String edit(){
		//准备数据
		Topic topic=topicService.findById(model.getId());
		topic.setTitle(model.getTitle());
		topic.setContent(model.getContent());
		topicService.update(topic);
		return "toShow";
	}
	//不被序列化
	public Long getForumId() {
		return forumId;
	}
	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
