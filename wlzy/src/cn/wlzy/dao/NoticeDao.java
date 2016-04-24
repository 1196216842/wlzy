package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;

public interface NoticeDao extends BaseDao<Notice> {
	public List<Notice> findByUser(User user);
	public List<Notice> findByUser(User user, PageRoll pageRoll);
/*	public List<Notice> findAll(PageRoll pageRoll);*/
	public List<Notice> findNotice(int size);
	/*public List<Notice> findNewNotice(int size);*/

}
