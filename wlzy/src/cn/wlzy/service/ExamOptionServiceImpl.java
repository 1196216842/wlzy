package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.dao.ExamOptionDao;
import cn.wlzy.domain.ExamOption;
import cn.wlzy.domain.ExamTopic;
@Transactional
@Service("examOptionService")
public class ExamOptionServiceImpl implements ExamOptionService {
	@Resource(name="examOptionDao")
	private ExamOptionDao examOptionDao;

	public List<ExamOption> findAll() {
		return examOptionDao.findAll();
	}

	public void save(ExamOption examOption) {
		examOptionDao.save(examOption);
	}

	public ExamOption findById(Long id) {
		return examOptionDao.findById(id);
	}

	public void update(ExamOption examOption) {
		examOptionDao.update(examOption);
	}

	public void delete(Long id) {
		examOptionDao.delete(id);
	}

	public List<ExamOption> findByExamTopic(ExamTopic examTopic) {
		return examOptionDao.findByExamTopic(examTopic);
	}

}
