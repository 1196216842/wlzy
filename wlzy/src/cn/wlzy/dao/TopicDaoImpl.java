package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Forum;
import cn.wlzy.domain.Topic;
import cn.wlzy.domain.User;

@SuppressWarnings("unchecked")
@Repository("topicDao")
public class TopicDaoImpl extends BaseDaoImpl<Topic> implements TopicDao {
	/**
	 * 查询指定版块中的所有主题;排序：所有置顶帖在最上面，并按最后更新时间排序，让新状态的在最上面
	 */
	public List<Topic> findByForum(Forum forum) {
		return getSession()
				.createQuery(//
						"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		// 设置特殊属性并保存
		topic.setLastReply(null);
		topic.setLastUpdateTime(topic.getPostTime());//第一次保存的时候，发布时间就为更新时间
		topic.setReplyCount(0);
		topic.setType(Topic.TYPE_NORMAL);
		getSession().save(topic);
		// 维护相关的特殊属性
		Forum forum = topic.getForum();
		
		forum.setTopicCount(forum.getTopicCount() + 1);//主题数量  
		forum.setArticleCount(forum.getArticleCount() + 1);//文章数量（主题加回复）
		forum.setLastTopic(topic);//最后发表的主题
		getSession().update(forum);
	}

	public Topic findLastTopic(Forum forum) {
		if(findByForum(forum).size()!=0){
			return (Topic)findByForum(forum).toArray()[0];
		}else{
			return null;
		}
	}

	public List<Topic> findNewTopics(int size) {
		List<Topic> topics=getSession().createQuery(//
								"FROM Topic t ORDER BY t.id DESC")//
								.setFirstResult(0)//
								.setMaxResults(size)//
								.list();
//		System.out.println(topics);
		return topics;
		
	}
	public List<Topic> findHotTopics() {
		List<Topic> topics=getSession().createQuery(//
				"FROM Topic t ORDER BY t.replyCount DESC")//
				.setFirstResult(0)//
				.setMaxResults(6)//
				.list();
		return topics;
	}
	public List<Topic> findByUser(User user) {
		return getSession()
				.createQuery(//
						"FROM Topic t WHERE t.author=?")//
				.setParameter(0, user)//
				.list();
	}

	public List<Topic> findByForum(Forum forum, PageRoll pageRoll) {
		//先查询出总记录（totalCount）数先
		Object object=getSession().createQuery(//
				"SELECT count(*) FROM Topic WHERE forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();
		String value=String.valueOf(object);
		int totalCount=Integer.parseInt(value);
		pageRoll.setTotalCount(totalCount);

		//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
		List<Topic> list=getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
				.setMaxResults(pageRoll.getPageSize())//
				.list();
		return list;
	}

	public List<Topic> findByUser(User user, PageRoll pageRoll) {
		//先查询出总记录（totalCount）数先
		Object object=getSession().createQuery(//
					"SELECT count(*) FROM Topic t WHERE t.author=?")//
					.setParameter(0, user)//
					.uniqueResult();
			String value=String.valueOf(object);
			int totalCount=Integer.parseInt(value);
			pageRoll.setTotalCount(totalCount);

			//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
			pageRoll.setPageSize(20);
			List<Topic> list=getSession().createQuery(//
						"FROM Topic t WHERE t.author=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC,t.lastUpdateTime DESC")//
						.setParameter(0, user)//
						.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
						.setMaxResults(pageRoll.getPageSize())//
						.list();
				return list;
	}

}
