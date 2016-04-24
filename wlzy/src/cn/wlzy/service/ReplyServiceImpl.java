package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.base.PageRoll;
import cn.wlzy.dao.ReplyDao;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;
@Transactional
@Service("replyService")
public class ReplyServiceImpl implements ReplyService {
	@Resource(name="replyDao")
	private ReplyDao replyDao;
	public List<Reply> findByTopic(Topic topic) {
		return replyDao.findByTopic(topic);
	}
	public void save(Reply reply) {
		replyDao.save(reply);
		
	}
	public Reply findById(Long id) {
		return replyDao.findById(id);
	}
	public void delete(Long id) {
		replyDao.delete(id);
	}
	public Reply findLastReply(Topic topic) {
		return replyDao.findLastReply(topic);
	}
	public void update(Reply reply) {
		replyDao.update(reply); 
	}
	public List<Reply> findByUser(User user) {
		return replyDao.findByUser(user);
	}
	public List<Reply> findByTopic(Topic topic, PageRoll pageRoll) {
		return replyDao.findByTopic(topic, pageRoll);
	}

}
