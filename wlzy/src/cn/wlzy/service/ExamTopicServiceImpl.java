package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.dao.ExamTopicDao;
import cn.wlzy.domain.ExamTopic;
@Transactional
@Service("examTopicService")
public class ExamTopicServiceImpl implements ExamTopicService {
	@Resource(name="examTopicDao")
	private ExamTopicDao examTopicDao;

	public List<ExamTopic> findAll() {
		return examTopicDao.findAll();
	}

	public void save(ExamTopic examTopic) {
		examTopicDao.save(examTopic);		
	}

	public ExamTopic findById(Long id) {
		return examTopicDao.findById(id);
	}

	public void update(ExamTopic examTopic) {
		examTopicDao.update(examTopic);
	}

	public void delete(Long id) {
		examTopicDao.delete(id);
	}

}
