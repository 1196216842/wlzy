package cn.wlzy.action;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import cn.wlzy.domain.ExamOption;
import cn.wlzy.domain.ExamTopic;
import cn.wlzy.service.ExamOptionService;
import cn.wlzy.service.ExamTopicService;
@Controller("examOptionAction")
@Scope("prototype")
public class ExamOptionAction extends ActionSupport implements Serializable,
		ModelDriven<ExamOption> {
	private static final long serialVersionUID = 1L;
	private ExamOption model=new ExamOption();
	private Long examTopicId;
	
	public Long getExamTopicId() {
		return examTopicId;
	}

	public void setExamTopicId(Long examTopicId) {
		this.examTopicId = examTopicId;
	}
	@Resource(name="examOptionService")
	private ExamOptionService examOptionService;
	@Resource(name="examTopicService")
	private ExamTopicService examTopicService;
	public ExamOption getModel() {
		return model;
	}
	
/*	public String list(){
		List<ExamOption> examOptions= examOptionService.findAll();
		ActionContext.getContext().put("examOptions", examOptions);
		return "list";
	}*/
	public String show(){
		ExamTopic examTopic=examTopicService.findById(examTopicId);
		List<ExamOption> examOptions= examOptionService.findByExamTopic(examTopic);
		ActionContext.getContext().put("examTopic", examTopic);
		ActionContext.getContext().put("examOptions", examOptions);
		return "show";
	}
}
