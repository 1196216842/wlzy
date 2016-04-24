package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;

@Repository("noticeDao")
public class NoticeDaoImpl extends BaseDaoImpl<Notice> implements NoticeDao {

	@SuppressWarnings("unchecked")
	public List<Notice> findByUser(User user) {
		return getSession()
					.createQuery(//
					"FROM Notice t WHERE t.user=?")//
					.setParameter(0, user)//
					.list();
	}

	@SuppressWarnings("unchecked")
	public List<Notice> findByUser(User user, PageRoll pageRoll) {
				Object object;
				if(user!=null){
					//先查询出总记录（totalCount）数先
					object=getSession().createQuery(//
						"SELECT count(*) FROM Notice n WHERE n.user=?")//
						.setParameter(0, user)//
						.uniqueResult();
				}else{
					//先查询出总记录（totalCount）数先
					object=getSession().createQuery(//
						"SELECT count(*) FROM Notice")//
						.uniqueResult();
				}
				
				String value=String.valueOf(object);
				int totalCount=Integer.parseInt(value);
				pageRoll.setTotalCount(totalCount);
				//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
				List<Notice> list;
				if(user!=null){
					list=getSession().createQuery(//
							"FROM Notice n WHERE n.user=? ORDER BY n.postTime DESC")//
							.setParameter(0, user)//
							.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
							.setMaxResults(pageRoll.getPageSize())//
							.list();
				}else{
					list=getSession().createQuery(//
							"FROM Notice n ORDER BY n.postTime DESC")//
							.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
							.setMaxResults(pageRoll.getPageSize())//
							.list();
				}
				return list;
	}
/*
	public List<Notice> findAll(PageRoll pageRoll) {
		// TODO Auto-generated method stub
		return null;
	}*/

	@SuppressWarnings("unchecked")
	public List<Notice> findNotice(int size) {
		return getSession().createQuery(//
				"FROM Notice n ORDER BY n.postTime DESC")//
				.setFirstResult(0)//
				.setMaxResults(size)//
				.list();
	}

	/*public List<Notice> findNewNotice(int size) {
		// TODO Auto-generated method stub
		return null;
	}*/

}
