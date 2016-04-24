package cn.wlzy.service;

import java.util.List;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.User;

public interface UserService {
	public void save(User user);
	public void delete(Long id);
	public void update(User user);
	public User findById(Long id);
	public List<User> findByIds(Long[] ids);
	public List<User> findAll();
	public User findByUsernameAndPasswordAndUserType(String username, String password,int userType);
	public List<User> findPageRoll(PageRoll pageRoll);
	public List<User> findTeacher(int userType);
}
