package com.donation.query;

public class LayUIQuery {
	//layui传到后台的页码
	private Integer page;
	//layui传到后台的每页记录条数
	private Integer limit;
	//跳过数据库的前多少条数据
	private Integer skipRows;
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getSkipRows() {
		return skipRows;
	}
	public void setSkipRows(Integer skipRows) {
		this.skipRows = skipRows;
	}
	
}
