package cn.wlzy.action;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;
import cn.wlzy.service.NoticeService;
import cn.wlzy.service.UserService;
@Controller("noticeAction")
@Scope("prototype")
public class NoticeAction extends ActionSupport implements Serializable,
		ModelDriven<Notice> {
	private static final long serialVersionUID = 1L;
	private Notice model=new Notice();
	private Long userId=0l;
	private int size=20;//默认查询十条数据
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	private int currentPage=1;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	@Resource(name="noticeService")
	private NoticeService noticeService;
	@Resource(name="userService")
	private UserService userService;
	public Notice getModel() {
		return model;
	}
	
	/**
	 * 根据ID查找目的通知和最新发的十条通知
	 * @return
	 */
	public String show(){
		//ID查找出对应通知
		Notice notice=noticeService.findById(model.getId());
		//查找最新的十条帖子
		List<Notice> notices=noticeService.findNotice(size);
		ActionContext.getContext().put("notice", notice);
		ActionContext.getContext().put("notices", notices);
		return "show";
	}
	
	public String addUI(){
		/*ActionContext.getContext().put("userId", userId);*/
		return "addUI";
	}
	/**
	 * 教师发布或者管理员发布
	 * @return
	 */
	public String add(){
		User user=(User) ActionContext.getContext().getSession().get("user");
		/**
		 * title,content,type都是页面传递过来存放在model当中
		 */
		model.setPostTime(new Date());
		model.setUser(user);
		noticeService.save(model);
		System.out.println(model.getContent());
		return "toTeacherList";
	}
	/**
	 * 只有管理员才可以修改
	 * @return
	 */
	public String editUI(){
		//准备数据（修改只要是老师就可以）并且是当前用户
		/*ActionContext.getContext().put("userId", userId);*/
		Notice notice=noticeService.findById(model.getId());
		ActionContext.getContext().put("notice", notice);
		return "editUI";
	}
	/**
	 * 管理员修改后，发布者信息应该不会变
	 * 时间也不会变。只是单纯的修改标题和内容
	 * 老师不能修改，除非发布一个新的更好
	 * @return
	 */
	public String edit(){
		Notice notice=noticeService.findById(model.getId());
		//User user=(User) ActionContext.getContext().getSession().get("user");
		notice.setTitle(model.getTitle());
		notice.setType(model.getType());
		notice.setContent(model.getContent());
		//notice.setPostTime(new Date());
		//notice.setUser(user);
		noticeService.update(notice);
		return "toTeacherList";
	}
	public String delete(){
		noticeService.delete(model.getId());
		return "toTeacherList";
	}
	/**
	 * 只有老师或管理员才有权限查看:这里放在老师简介里
	 * @return
	 */
	public String findNoticeByUser(){
		User user=userService.findById(userId);
		PageRoll pageRoll=new PageRoll();
		pageRoll.setPageSize(5);
		List<Notice> notices=noticeService.findByUser(user, pageRoll);
		ActionContext.getContext().put("notices", notices);
		ActionContext.getContext().put("pageRoll", pageRoll);
		return "toTeacherList";
	}
	
}
