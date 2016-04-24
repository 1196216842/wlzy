package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.base.PageRoll;
import cn.wlzy.dao.TopicDao;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
@Transactional
@Service("topicService")
public class TopicServiceImpl implements TopicService {
	@Resource(name="topicDao")
	private TopicDao topicDao;
	public List<Topic> findByForum(Forum forum) {
		return topicDao.findByForum(forum);
	}
	public void save(Topic topic) {
		topicDao.save(topic);
	}
	public Topic findById(Long id) {
		
		return topicDao.findById(id);
	}
	public void update(Topic topic) {
		topicDao.update(topic);
	}
	public void delete(Long id) {
		topicDao.delete(id);
	}
	public Topic findLastTopic(Forum forum) {
		return topicDao.findLastTopic(forum);
	}
	public List<Topic> findNewTopics(int size) {
		return topicDao.findNewTopics(size);
	}
	public List<Topic> findByUser(User user) {
		return topicDao.findByUser(user);
	}
	public List<Topic> findByForum(Forum forum, PageRoll pageRoll) {
		return topicDao.findByForum(forum, pageRoll);
	}
	public List<Topic> findHotTopics() {
		return topicDao.findHotTopics();
	}
	public List<Topic> findByUser(User user, PageRoll pageRoll) {
		return topicDao.findByUser(user, pageRoll);
	}

}
