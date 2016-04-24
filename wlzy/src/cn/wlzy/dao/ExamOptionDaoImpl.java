package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.domain.ExamOption;
import cn.wlzy.domain.ExamTopic;

@Repository("examOptionDao")
public class ExamOptionDaoImpl extends BaseDaoImpl<ExamOption> implements ExamOptionDao {

	@SuppressWarnings("unchecked")
	public List<ExamOption> findByExamTopic(ExamTopic examTopic) {
		return getSession().createQuery(
				"FROM ExamOption e WHERE e.examTopic=? ORDER BY e.num")//
				.setParameter(0, examTopic).list();
	}

}
