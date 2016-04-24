package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;

public interface ReplyDao extends BaseDao<Reply> {

	public List<Reply> findByTopic(Topic topic);

	public Reply findLastReply(Topic topic);
	
	public List<Reply> findByUser(User user);

	public List<Reply> findByTopic(Topic topic, PageRoll pageRoll);

}
