package cn.wlzy.service;

import java.util.List;

import cn.wlzy.domain.Forum;

public interface ForumService {
	public void save(Forum forum);
	public void delete(Long id);
	public void update(Forum forum);
	public Forum findById(Long id);
	public List<Forum> findByIds(Long[] ids);
	public List<Forum> findAll();
	public void moveUp(Long id);
	public void moveDown(Long id);
}
