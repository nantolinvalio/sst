package cl.abcdin.sst.model.filters;

import org.apache.ibatis.session.RowBounds;

public class GridControl {
	private Integer offset;
	private Integer limit;
	private String orderBy;
	private String sortOrder;
	
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	} 
	
	public RowBounds getRowBounds() {
		return new RowBounds(this.offset, this.limit);
	}
	public String getSortOrder() {
		return sortOrder;
	}
	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
}
