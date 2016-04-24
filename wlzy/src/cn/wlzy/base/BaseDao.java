package cn.wlzy.base;

import java.util.List;


public interface BaseDao<T>{
	/**
	 * 保存
	 */
	public void save(T entity);
	/**
	 * 删除
	 */
	public void delete(Long id);
	/**
	 * 修改
	 */
	public void update(T entity);
	/**
	 * 根据ID查找
	 */
	public T findById(Long id);
	/**
	 * 根据一组ID查找
	 */
	public List<T> findByIds(Long[] ids);
	/**
	 * 查找所有
	 */
	public List<T> findAll();
	/**
	 * 分页查询
	 */
	public List<T> findByPageRoll(PageRoll pageRoll);
}
