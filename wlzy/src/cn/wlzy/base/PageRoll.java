package cn.wlzy.base;
/**
 * 分页的属性
 * @author forever
 *
 */
public class PageRoll {
	private int currentPage=1;//当前页，页面传过来的，默认为1
	private int pageSize=5;//每页显示的条数，自己设定的一般为20条,这个也可以页面传过来
	private int pageCount;//总页数，totalCount/pageSize;
	private int totalCount;//总记录数，查询出来的
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageCount() {
		pageCount=(totalCount+pageSize-1)/pageSize;
		return pageCount ;//分页
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	
}
