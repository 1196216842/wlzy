package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.domain.User;

public interface UserDao extends BaseDao<User>{

	public User findByUsernameAndPasswordAndUserType(String username, String password,int userType);

	public List<User> findTeacher(int userType);

}
