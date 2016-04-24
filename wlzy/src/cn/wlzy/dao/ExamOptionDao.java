package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.domain.ExamOption;
import cn.wlzy.domain.ExamTopic;

public interface ExamOptionDao extends BaseDao<ExamOption> {

	public List<ExamOption> findByExamTopic(ExamTopic examTopic);

}
