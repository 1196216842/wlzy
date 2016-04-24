package cn.wlzy.action;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
import cn.wlzy.service.ForumService;
import cn.wlzy.service.ReplyService;
import cn.wlzy.service.TopicService;
@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends ActionSupport implements Serializable,
		ModelDriven<Reply> {
	private static final long serialVersionUID = 1L;
	private Reply model=new Reply();
	@Resource(name="topicService")
	private TopicService topicService;
	@Resource(name="replyService")
	private ReplyService replyService;
	@Resource(name="forumService")
	private ForumService forumService;
	public Reply getModel() {
		return model;
	}
	private Long topicId;
	//发表新回复页面
/*	public String addUI(){
		//准备数据
		Topic topic=topicService.findById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}*/
	//转到回复主题的那个页面
	public String add(){
		//封装数据
		//>>表单字段，已经存在
//		model.setContent(content);
//		model.setTitle(title);
		model.setTopic(topicService.findById(topicId));
		//楼层加一默认为
		//所有回复里楼层数最大的加上一
		Topic topic=topicService.findById(topicId);
		//因为最后的回复是需要在后面才能添加，所以第一次请求可能会失败，因此加一个判断防止报错
		if(topic.getLastReply()!=null){
			int floor=topic.getLastReply().getFloor();
			model.setFloor(floor+1);
		}else{
			model.setFloor(1);
		}
		
		
		//>>当前直接获取的信息
		model.setAuthor((User)ActionContext.getContext().getSession().get("user"));
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		
		replyService.save(model);
		return "toTopicShow";
	}
	//删除回复
	public String delete(){
		Reply reply=replyService.findById(model.getId());
		//维护特殊的属性
		Topic topic=reply.getTopic();
		topic.setReplyCount(topic.getReplyCount()-1);//回复减1
		topic.getForum().setArticleCount(topic.getForum().getArticleCount()-1);//版块文章数量减一
		//把topicId准备好
		topicId=topic.getId();
		//如果删除的是最后回复，那么最后回复要解除关系，再把先前一回复赋给topic
		//1：找到最后的回复
		Reply lastReply=topic.getLastReply();
		if(reply.equals(lastReply)){
			//先解除关系
			topic.setLastReply(null);
			replyService.delete(model.getId());
			//找到删除后本主题的最后回复，设为lastReply
			lastReply=replyService.findLastReply(topic);
			topic.setLastReply(lastReply);
		}else{
			replyService.delete(model.getId());
		}
		//删除后再更新
		topicService.update(topic);
		forumService.update(topic.getForum());
		return "toTopicShow";
	}
	//修改页面
	public String editUI(){
		//准备数据
		Reply reply=replyService.findById(model.getId());
		System.out.println("+++:"+reply.getContent());
		Topic topic=reply.getTopic();
		ActionContext.getContext().put("topic", topic);
		ActionContext.getContext().put("reply",reply);
		return "editUI";
	}
	//修改
	public String edit(){
		//准备数据
		Reply reply=replyService.findById(model.getId());
		reply.setTitle(model.getTitle());
		reply.setContent(model.getContent());
		//把topicId准备好
		topicId=reply.getTopic().getId();
		replyService.update(reply);
		return "toTopicShow";
	}
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
	
	
}
