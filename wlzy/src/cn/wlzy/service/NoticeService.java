package cn.wlzy.service;

import java.util.List;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.Notice;
import cn.wlzy.domain.User;
public interface NoticeService {
	
	public List<Notice> findAll();
	public List<Notice> findByUser(User user);

	public void save(Notice notice);

	public Notice findById(Long id);

	public void update(Notice notice);

	public void delete(Long id);
/*	
	//全部和分页查询
	public List<Notice> findAll(PageRoll pageRoll);*/
	//用户和分页查询
	public List<Notice> findByUser(User user, PageRoll pageRoll);
	public List<Notice> findNotice(int size);
	/*public List<Notice> findNewNotice(int size);*/

}
