package cn.wlzy.dao;

import cn.wlzy.base.BaseDao;
import cn.wlzy.domain.Forum;

public interface ForumDao extends BaseDao<Forum> {

	public void moveUp(Long id);

	public void moveDown(Long id);

}
