package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.User;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{

	public User findByUsernameAndPasswordAndUserType(String username, String password,int userType) {
		User user=(User)getSession().createQuery(//
				"FROM User u WHERE u.username=? AND u.password=? AND u.userType=?")//
				.setParameter(0, username)//
				.setParameter(1, password)//
				.setParameter(2, userType)//
				.uniqueResult();
		return user;
	}
	@Override
	public List<User> findByPageRoll(PageRoll pageRoll) {
		//先查询出总记录（totalCount）数先
		Object object=getSession().createQuery(//
				"SELECT count(*) FROM User")//
				.uniqueResult();
		String value=String.valueOf(object);
		int totalCount=Integer.parseInt(value);
		pageRoll.setTotalCount(totalCount);
		//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
		pageRoll.setPageSize(20);
		@SuppressWarnings("unchecked")
		List<User> list=getSession().createQuery(//
				"FROM User")//
				.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
				.setMaxResults(pageRoll.getPageSize())//
				.list();
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<User> findTeacher(int userType) {
		return getSession().createQuery(//
				"FROM User WHERE userType=?")//
				.setParameter(0, userType).list();
	}

}
