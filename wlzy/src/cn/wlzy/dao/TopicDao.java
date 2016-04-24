package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;

public interface TopicDao extends BaseDao<Topic> {

	public List<Topic> findByForum(Forum forum);

	public Topic findLastTopic(Forum forum);

	public List<Topic> findNewTopics(int size);

	public List<Topic> findByUser(User user);

	public List<Topic> findByForum(Forum forum, PageRoll pageRoll);

	public List<Topic> findHotTopics();

	public List<Topic> findByUser(User user, PageRoll pageRoll);

}
