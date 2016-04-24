package cn.wlzy.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.wlzy.base.BaseDaoImpl;
import cn.wlzy.base.PageRoll;
import cn.wlzy.domain.MyFile;
import cn.wlzy.domain.User;
@Repository("myFileDao")
public class MyFileDaoImpl extends BaseDaoImpl<MyFile> implements MyFileDao {
	@SuppressWarnings("unchecked")
	public List<MyFile> findAllByType(int fileType) {
		//根据类型时间来排序:类型默认是查询视频
		return getSession().createQuery(//
				"FROM MyFile m WHERE m.fileType=? ORDER BY m.uploadTime DESC")//
				.setParameter(0, fileType)//
				.list();
	}
	//根据用户和类型来查找
	@SuppressWarnings("unchecked")
	public List<MyFile> findFileByUserAndTtype(User user, int fileType) {
		return getSession().createQuery(//
				"FROM MyFile m WHERE m.user=? AND m.fileType=? ORDER BY m.uploadTime DESC")//
				.setParameter(0, user)//
				.setParameter(1, fileType)//
				.list();
	}
	@SuppressWarnings("unchecked")
	public List<MyFile> findFileByUser(User user) {
		return getSession().createQuery(//
				"FROM MyFile m WHERE m.user=?")//
				.setParameter(0, user)//
				.list();
	}
	//用户若为空，则代表查询全部
	@SuppressWarnings("unchecked")
	public List<MyFile> findFileByUserAndTtype(User user, int fileType,
			PageRoll pageRoll) {
		Object object;
		if(user!=null){
			//先查询出总记录（totalCount）数先
			object=getSession().createQuery(//
				"SELECT count(*) FROM MyFile m WHERE m.user=? AND m.fileType=?")//
				.setParameter(0, user)//
				.setParameter(1, fileType)//
				.uniqueResult();
		}else{
			//先查询出总记录（totalCount）数先
			object=getSession().createQuery(//
				"SELECT count(*) FROM MyFile m WHERE m.fileType=?")//
				.setParameter(0, fileType)//
				.uniqueResult();
		}
		
		String value=String.valueOf(object);
		int totalCount=Integer.parseInt(value);
		pageRoll.setTotalCount(totalCount);
		//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
		List<MyFile> list;
		if(user!=null){
			list=getSession().createQuery(//
					"FROM MyFile m WHERE m.user=? AND m.fileType=? ORDER BY m.uploadTime DESC")//
					.setParameter(0, user)//
					.setParameter(1, fileType)//
					.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
					.setMaxResults(pageRoll.getPageSize())//
					.list();
		}else{
			list=getSession().createQuery(//
					"FROM MyFile m WHERE m.fileType=? ORDER BY m.uploadTime DESC")//
					.setParameter(0, fileType)//
					.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
					.setMaxResults(pageRoll.getPageSize())//
					.list();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<MyFile> findNewVideo(int size, int fileType) {
		return getSession().createQuery(//
				"FROM MyFile f WHERE f.fileType=? ORDER BY f.uploadTime DESC")//
				.setParameter(0, fileType)//
				.setFirstResult(0)//
				.setMaxResults(size)//
				.list();
	}
	@SuppressWarnings("unchecked")
	public List<MyFile> findNewMyFile(int size, int type) {
		// TODO Auto-generated method stub
		return getSession().createQuery(//
				"FROM MyFile f WHERE f.fileType=? ORDER BY f.uploadTime DESC")//
				.setParameter(0, type)//
				.setFirstResult(0)//
				.setMaxResults(size)//
				.list();
	}
}
