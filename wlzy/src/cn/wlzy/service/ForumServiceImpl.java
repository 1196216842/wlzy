package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.dao.ForumDao;
import cn.wlzy.domain.Forum;
@Service("forumService")
@Transactional
public class ForumServiceImpl implements ForumService {
	@Resource(name="forumDao")
	private ForumDao forumDao;
	public void save(Forum forum) {
		forumDao.save(forum);
	}

	public void delete(Long id) {
		forumDao.delete(id);
	}

	public void update(Forum forum) {
		forumDao.update(forum);
	}

	public Forum findById(Long id) {
		return forumDao.findById(id);
	}

	public List<Forum> findByIds(Long[] ids) {
		return forumDao.findByIds(ids);
	}

	public List<Forum> findAll() {
		return forumDao.findAll();
	}

	public void moveUp(Long id) {
		forumDao.moveUp(id);
	}

	public void moveDown(Long id) {
		forumDao.moveDown(id);
	}

}
