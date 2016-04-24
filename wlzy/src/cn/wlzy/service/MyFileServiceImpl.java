package cn.wlzy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.wlzy.base.PageRoll;
import cn.wlzy.dao.MyFileDao;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.User;
@Service("myFileService")
@Transactional
public class MyFileServiceImpl implements MyFileService {
	@Resource(name="myFileDao")
	private MyFileDao myFileDao;

	public void save(MyFile myFile) {
		myFileDao.save(myFile);		
	}

	public void delete(Long id) {
		myFileDao.delete(id);
	}

	public void update(MyFile myFile) {
		myFileDao.update(myFile);
	}

	public MyFile findById(Long id) {
		return myFileDao.findById(id);
	}

	public List<MyFile> findByIds(Long[] ids) {
		return myFileDao.findByIds(ids);
	}

	public List<MyFile> findAll() {
		return myFileDao.findAll();
	}
	public List<MyFile> findAllByType(int fileType){
		return myFileDao.findAllByType(fileType);
	}

	public List<MyFile> findFileByUserAndTtype(User user, int fileType) {
		return myFileDao.findFileByUserAndTtype(user, fileType);
	}

	public List<MyFile> findFileByUser(User user) {
		return myFileDao.findFileByUser(user);
	}

	public List<MyFile> findFileByUserAndTtype(User user, int fileType,
			PageRoll pageRoll) {
		return myFileDao.findFileByUserAndTtype(user, fileType,pageRoll);
	}

	public List<MyFile> findNewVideo(int size, int fileType) {
		return myFileDao.findNewVideo(size,fileType);
	}

	public List<MyFile> findNewMyFile(int size, int type) {
		// TODO Auto-generated method stub
		return myFileDao.findNewMyFile(size,type);
	}

}
