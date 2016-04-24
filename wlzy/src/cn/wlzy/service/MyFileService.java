package cn.wlzy.service;

import java.util.List;

import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.User;

public interface MyFileService {
	public void save(MyFile myFile);
	public void delete(Long id);
	public void update(MyFile myFile);
	public MyFile findById(Long id);
	public List<MyFile> findByIds(Long[] ids);
	public List<MyFile> findAll();
	public List<MyFile> findAllByType(int fileType);
	public List<MyFile> findFileByUserAndTtype(User user, int fileType,PageRoll pageRoll);
	public List<MyFile> findFileByUser(User user);
	public List<MyFile> findNewVideo(int size, int fileType);
	public List<MyFile> findNewMyFile(int size, int type);
}
