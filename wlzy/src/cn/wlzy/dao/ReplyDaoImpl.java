package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Reply;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;

@SuppressWarnings("unchecked")
@Repository("replyDao")
public class ReplyDaoImpl extends BaseDaoImpl<Reply> implements ReplyDao{
	
	public List<Reply> findByTopic(Topic topic) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
				.setParameter(0, topic)//
				.list();
	}
	//为了删除用户
	public List<Reply> findByUser(User user) {
		return getSession().createQuery(//
				"FROM Reply r WHERE r.author=?")//
				.setParameter(0, user)//
				.list();
	}
	@Override
	public void save(Reply reply) {
		getSession().save(reply);//保存
		//维护相关信息
		Topic topic=reply.getTopic();
		Forum forum=topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);//文章数量（主题加回复）
		topic.setReplyCount(topic.getReplyCount()+1);//回复数量
		topic.setLastReply(reply);//最后发表的回复
		topic.setLastUpdateTime(reply.getPostTime());//最后更新的时间
		
		getSession().update(topic);
		getSession().update(forum);
		
	}
	public Reply findLastReply(Topic topic) {
		if(findByTopic(topic).size()!=0){
			//找到最后一个
			return (Reply)findByTopic(topic).toArray()[findByTopic(topic).size()-1];
		}else{
			return null;
		}
	}
	public List<Reply> findByTopic(Topic topic, PageRoll pageRoll) {
		//先查询出总记录（totalCount）数先
		Object object=getSession().createQuery(//
					"SELECT count(*) FROM Reply WHERE topic=?")//
					.setParameter(0, topic)//
					.uniqueResult();
		String value=String.valueOf(object);
		int totalCount=Integer.parseInt(value);
		pageRoll.setTotalCount(totalCount);

		//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
		List<Reply> list=getSession().createQuery(//
						"FROM Reply r WHERE r.topic=? ORDER BY r.postTime ASC")//
						.setParameter(0, topic)//
						.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
						.setMaxResults(pageRoll.getPageSize())//
						.list();
		return list;
	}

}
