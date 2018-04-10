package com.donation.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "articles")
public class Articles {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "RELEASE_DATE")
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	private Date releaseDate;
	@Column(name = "PUBLISHER_ID")
	private Long publisherId;
	@Column(name = "PAGE_VIEW")
	private Long pageView;
	@Column(name = "PUBLISHING_LOCATION")
	private String publishingLocation;
	@Column(name = "TITLE")
	private String title;
	@Column(name = "STATUS")
	private Integer status;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Long getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(Long publisherId) {
		this.publisherId = publisherId;
	}
	public Long getPageView() {
		return pageView;
	}
	public void setPageView(Long pageView) {
		this.pageView = pageView;
	}
	public String getPublishingLocation() {
		return publishingLocation;
	}
	public void setPublishingLocation(String publishingLocation) {
		this.publishingLocation = publishingLocation;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Articles [id=" + id + ", releaseDate=" + releaseDate + ", publisherId=" + publisherId + ", pageView="
				+ pageView + ", publishingLocation=" + publishingLocation + "]";
	}
	
	
	
}
