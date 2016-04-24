package cn.wlzy.service;

import java.util.List;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
public interface TopicService {

	public List<Topic> findByForum(Forum forum);

	public void save(Topic model);

	public Topic findById(Long id);

	public void update(Topic topic);

	public void delete(Long id);

    public Topic findLastTopic(Forum forum);

	public List<Topic> findNewTopics(int size);

	public List<Topic> findByUser(User user);
	public List<Topic> findByUser(User user, PageRoll pageRoll);

	public List<Topic> findByForum(Forum forum, PageRoll pageRoll);

	public List<Topic> findHotTopics();

}
