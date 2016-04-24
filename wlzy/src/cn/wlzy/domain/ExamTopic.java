package cn.wlzy.domain;

import java.util.Date;
import java.util.Set;

/**
 * 试卷题目，名称
 * @author Administrator
 *
 */
public class ExamTopic {
	private Long id;//ID
	private String name;//名称
	private Date uploadTime;//上传时间
	private Set<ExamOption> examOptions;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public Set<ExamOption> getExamOptions() {
		return examOptions;
	}
	public void setExamOptions(Set<ExamOption> examOptions) {
		this.examOptions = examOptions;
	}
	
}
