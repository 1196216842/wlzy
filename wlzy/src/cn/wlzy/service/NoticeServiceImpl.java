package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.base.PageRoll;
import cn.wlzy.dao.NoticeDao;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;
@Transactional
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	@Resource(name="noticeDao")
	private NoticeDao noticeDao;

	public List<Notice> findAll() {
		return noticeDao.findAll();
	}
	public void save(Notice notice) {
		noticeDao.save(notice);
	}

	public Notice findById(Long id) {
		return noticeDao.findById(id);
	}

	public void update(Notice notice) {
		noticeDao.update(notice);
	}

	public void delete(Long id) {
		noticeDao.delete(id);
	}

	public List<Notice> findByUser(User user) {
		return noticeDao.findByUser(user);
	}

	public List<Notice> findByUser(User user, PageRoll pageRoll) {
		return noticeDao.findByUser(user,pageRoll);
	}
	/*public List<Notice> findAll(PageRoll pageRoll) {
		return noticeDao.findAll(pageRoll);
	}*/
	public List<Notice> findNotice(int size) {
		// TODO Auto-generated method stub
		return noticeDao.findNotice(size);
	}
/*	public List<Notice> findNewNotice(int size) {
		// TODO Auto-generated method stub
		return noticeDao.findNewNotice(size);
	}*/


}
