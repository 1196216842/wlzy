package cn.wlzy.dao;

import java.util.List;

import cn.wlzy.base.BaseDao;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.User;


public interface MyFileDao extends BaseDao<MyFile> {

	public List<MyFile> findFileByUserAndTtype(User user, int fileType);
	public List<MyFile> findAllByType(int fileType);
	public List<MyFile> findFileByUser(User user);
	public List<MyFile> findFileByUserAndTtype(User user, int fileType,
			PageRoll pageRoll);
	public List<MyFile> findNewVideo(int size, int video);
	public List<MyFile> findNewMyFile(int size, int type);
}
