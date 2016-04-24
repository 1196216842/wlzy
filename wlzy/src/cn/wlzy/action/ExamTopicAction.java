package cn.wlzy.action;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
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
import cn.wlzy.utils.ReadExcel;
@Controller("examTopicAction")
@Scope("prototype")
public class ExamTopicAction extends ActionSupport implements Serializable,
		ModelDriven<ExamTopic> {
	private static final long serialVersionUID = 1L;
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
	private File file;
	//提交过来的file的MIME类型
	private String fileContentType;//用来判断格式是否符合
	//提交过来的file的名字
	private String fileFileName;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	private ExamTopic model=new ExamTopic();
	@Resource(name="examTopicService")
	private ExamTopicService examTopicService;
	@Resource(name="examOptionService")
	private ExamOptionService examOptionService;
	public ExamTopic getModel() {
		return model;
	}
	
	public String list(){
		List<ExamTopic> examTopics= examTopicService.findAll();
		ActionContext.getContext().put("examTopics", examTopics);
		return "list";
	}
	
	public String uploadUI(){
		return "uploadUI";
	}
	/**
	 * 上传的试卷不需要保存，因为是excel所以只需要解析即可，
	 * 并且只解析EXCEL的第一张表
	 * @return
	 */
	public String upload(){
		ExamTopic examTopic=new ExamTopic();
		try {
			System.out.println("文件名："+fileFileName);
			String name=fileFileName.substring(0,fileFileName.lastIndexOf("."));
			System.out.println(name);
			String type=fileFileName.substring(fileFileName.lastIndexOf(".")+1,fileFileName.length());
			examTopic.setName(name);
			examTopic.setUploadTime(new Date());
			examTopicService.save(examTopic);
			List<ExamOption> examOptions=ReadExcel.readExcel(file, type);
			for (int i = 0; i < examOptions.size(); i++) {
				System.out.println(examOptions.get(i));
				examOptions.get(i).setExamTopic(examTopic);
				examOptionService.save(examOptions.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("文件格式不正确");
			//在这里要把保存的题目名字删除掉
			examTopicService.delete(examTopic.getId());
			ActionContext.getContext().put("message", "文件格式不正确");
			return "uploadUI";
		}
		return "toList";
	}
	/**
	 * 删除
	 */
	public String delete(){
		examTopicService.delete(model.getId());
		return "toList";
	}
	/**
	 * 修改页面
	 */
	public String editUI(){
		//准备数据
		ExamTopic  examTopic =examTopicService.findById(model.getId());
		List<ExamOption> examOptions=examOptionService.findByExamTopic(examTopic);
		ActionContext.getContext().put("examTopic", examTopic);
		ActionContext.getContext().put("examOptions", examOptions);
		return "editUI";
	}
	/**
	 * 修改
	 * @return
	 */
	private Long[] ids;
	private String[] nums;
	private String[] contents;
	private String[] As;
	private String[] Bs;
	private String[] Cs;
	private String[] Ds;
	private String[] answers;
	
	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public String[] getNums() {
		return nums;
	}

	public void setNums(String[] nums) {
		this.nums = nums;
	}

	public String[] getContents() {
		return contents;
	}

	public void setContents(String[] contents) {
		this.contents = contents;
	}

	public String[] getAs() {
		return As;
	}

	public void setAs(String[] as) {
		As = as;
	}

	public String[] getBs() {
		return Bs;
	}

	public void setBs(String[] bs) {
		Bs = bs;
	}

	public String[] getCs() {
		return Cs;
	}

	public void setCs(String[] cs) {
		Cs = cs;
	}

	public String[] getDs() {
		return Ds;
	}

	public void setDs(String[] ds) {
		Ds = ds;
	}

	public String[] getAnswers() {
		return answers;
	}

	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	private ExamOption examOption;
	public String edit(){
		for(int i=0;i<ids.length;i++){
			examOption=examOptionService.findById(ids[i]);
			examOption.setNum(nums[i]);
			examOption.setContent(contents[i]);
			examOption.setA(As[i]);
			examOption.setB(Bs[i]);
			examOption.setC(Cs[i]);
			examOption.setD(Ds[i]);
			examOption.setAnswer(answers[i]);
			examOptionService.update(examOption);
		}
		return "toShow";
	}
}
