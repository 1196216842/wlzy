package cn.wlzy.service;

import java.util.List;

import cn.wlzy.domain.ExamOption;
import cn.wlzy.domain.ExamTopic;
public interface ExamOptionService {
	
	public List<ExamOption> findAll();

	public void save(ExamOption examOption);

	public ExamOption findById(Long id);

	public void update(ExamOption examOption);

	public void delete(Long id);
	
	public List<ExamOption> findByExamTopic(ExamTopic examTopic);
}
