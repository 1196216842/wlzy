package cn.wlzy.service;

import java.util.List;

import cn.wlzy.domain.ExamTopic;
public interface ExamTopicService {
	
	public List<ExamTopic> findAll();

	public void save(ExamTopic examTopic);

	public ExamTopic findById(Long id);

	public void update(ExamTopic examTopic);

	public void delete(Long id);
}
