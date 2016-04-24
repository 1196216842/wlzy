package cn.wlzy.service;

import java.util.List;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;

public interface ReplyService {

	public List<Reply> findByTopic(Topic topic);

	public void save(Reply reply);

	public Reply findById(Long id);

	public void delete(Long id);

	public Reply findLastReply(Topic topic);

	public void update(Reply reply);
	
	public List<Reply> findByUser(User user);

	public List<Reply> findByTopic(Topic topic, PageRoll pageRoll);

}
