package com.donation.query;

public class ArticlePageQuery {
	private Integer page;	//页码
	
	private Integer count = 21;	//每页显示的条数，默认设置为21条
	
	private Long skipRows;	//mysql需要limit的条数

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Long getSkipRows() {
		return skipRows;
	}

	public void setSkipRows(Long skipRows) {
		this.skipRows = skipRows;
	}
	
}
