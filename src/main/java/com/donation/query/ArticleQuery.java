package com.donation.query;

public class ArticleQuery {
	private String area;
	private String view;	//是否按浏览量排序，不为空则按此排序
	//传到后台的页码
	private Integer page;
	//传到后台的每页记录条数
	private Integer limit;
	//跳过数据库的前多少条数据
	private Integer skipRows;
	
	
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
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
