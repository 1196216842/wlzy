package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.base.PageRoll;
import cn.wlzy.dao.UserDao;
import cn.wlzy.domain.User;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	@Resource(name="userDao")
	private UserDao userDao;
	public void save(User user) {
		userDao.save(user);
	}

	public void delete(Long id) {
		userDao.delete(id);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public User findById(Long id) {
		return userDao.findById(id);
	}

	public List<User> findByIds(Long[] ids) {
		return userDao.findByIds(ids);
	}

	public List<User> findAll() {
		return userDao.findAll();
	}

	public User findByUsernameAndPasswordAndUserType(String username, String password,int userType) {
		return userDao.findByUsernameAndPasswordAndUserType(username,password,userType);
	}

	public List<User> findPageRoll(PageRoll pageRoll) {
		return userDao.findByPageRoll(pageRoll);
	}

	public List<User> findTeacher(int userType) {
		return userDao.findTeacher(userType) ;
	}

}
