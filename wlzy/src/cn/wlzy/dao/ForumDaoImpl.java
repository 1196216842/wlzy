package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.domain.Forum;
@Repository("forumDao")
public class ForumDaoImpl extends BaseDaoImpl<Forum> implements ForumDao {
	
	//重写查询,让它按位置升序查询
	@SuppressWarnings("unchecked")
	public List<Forum> findAll() {
		return getSession().createQuery(//
				"FROM Forum f  ORDER BY f.position")//
				.list();
		
	}
	
	//重写添加方法让属性position与id相同
	public void save(Forum forum) {
		getSession().save(forum);
		forum.setPosition(forum.getId().intValue());
		getSession().update(forum);
	}
	
	public void moveUp(Long id) {
		//找出要上移的forum
		Forum forum=findById(id);
		//找出上一个forum
		Forum other=(Forum)getSession().createQuery(//
				"FROM Forum f WHERE f.position<? ORDER BY f.position DESC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		
		if(other==null){
			return;//不能上移
		}
		//交换posiotion
		int temp=forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		
		//更新到数据库中
		getSession().update(forum);
		getSession().update(other);
	}

	public void moveDown(Long id) {
		//找出要下移的forum
		Forum forum=findById(id);
		//找出下一个forum
		Forum other=(Forum)getSession().createQuery(//
				"FROM Forum f WHERE f.position>? ORDER BY f.position ASC")//
				.setParameter(0, forum.getPosition())//
				.setFirstResult(0)//
				.setMaxResults(1)//
				.uniqueResult();
		if(other==null){
			return;//不能下移
		}
		//交换posiotion
		int temp=forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
				
		//更新到数据库中
		getSession().update(forum);
		getSession().update(other);
	}

}
