package cn.wlzy.base;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {
	private Class<T> clazz;
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	public BaseDaoImpl(){
		//使用反射技术得到T的真实类型
		ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
		this.clazz=(Class<T>)pt.getActualTypeArguments()[0];
	}
	//获得当前线程的session
	public Session getSession(){
		return sessionFactory.getCurrentSession();
	}
	public void save(T entity) {
			getSession().save(entity);
	}

	public void delete(Long id) {
		T entity=this.findById(id);
		if(entity!=null){
			getSession().delete(entity);
		}
	}
	
	public void update(T entity) {
		getSession().update(entity);
	}

	public T findById(Long id) {
		if(id==null){
			return null;
		}else{
			return (T)getSession().get(clazz, id);
		}
	}

	public List<T> findByIds(Long[] ids) {
		if(ids==null||ids.length==0){
			return Collections.EMPTY_LIST;//这样比返回null好
		}else{
			return getSession().createQuery(//
					"from " +clazz.getSimpleName()+" where id in (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}

	public List<T> findAll() {
		return getSession().createQuery(//
				"from "+clazz.getSimpleName())//
				.list();
	}
	/**
	 * 可能每个类的查询实现不同，那么再自己重写
	 */
	public List<T> findByPageRoll(PageRoll pageRoll) {
		//先查询出总记录（totalCount）数先
		Object object=getSession().createQuery(//
				"SELECT count(*) FROM "+clazz.getSimpleName())//
				.uniqueResult();
		String value=String.valueOf(object);
		int totalCount=Integer.parseInt(value);
		pageRoll.setTotalCount(totalCount);
		
		//查询数据SELECT * FROM USER LIMIT (（当前页-1）*每页显示的条数),(每页显示的条数)
		List<T> list=getSession().createQuery(//
				"FROM "+clazz.getSimpleName())//
				.setFirstResult((pageRoll.getCurrentPage()-1)*pageRoll.getPageSize())//
				.setMaxResults(pageRoll.getPageSize())//
				.list();
		return list;
	}

}
