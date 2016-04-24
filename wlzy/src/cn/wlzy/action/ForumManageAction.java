package cn.wlzy.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.domain.Forum;
import cn.wlzy.service.ForumService;
@Controller("forumManageAction")
@Scope("prototype")
public class ForumManageAction extends ActionSupport implements ModelDriven<Forum>,
		Serializable {
	private static final long serialVersionUID = 1L;
	private Forum model=new Forum();
	public Forum getModel() {
		return model;
	}
	@Resource(name="forumService")
	private ForumService forumService;
	public String list(){
		List<Forum> forums=forumService.findAll();
		ActionContext.getContext().put("forums", forums);
		return "list";
	}
	public String delete(){
		forumService.delete(model.getId());
		return "toList";
	}
	public String addUI(){
		return "addUI";
	}
	public String add(){
		forumService.save(model);
		return "toList";
	}
	public String editUI(){
		//准备回显数据
		Forum forum=forumService.findById(model.getId());
		ActionContext.getContext().put("forum",forum);
		return "editUI";
	}
	public String edit(){
		//获得修改的对象
		Forum forum=forumService.findById(model.getId());
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		forumService.update(forum);
		return "toList";
	}
	/**
	 * 上移
	 */
	public String moveUp(){
		forumService.moveUp(model.getId());
		return "toList";
	}
	/**
	 * 下移
	 */
	public String moveDown(){
		forumService.moveDown(model.getId());
		return "toList";
	}
}
