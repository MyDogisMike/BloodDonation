package com.donation.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "article_content")
public class ArticleContent {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ARTICLE_ID")
	private Long articleId;
	@Column(name = "CONTENT")
	private String content;
	@Column(name = "TYPE")
	private Integer type;
	@Column(name = "SEQUENCE")
	private Integer sequence;
	@Column(name = "BELONG")
	private Integer belong;
	
	public Integer getBelong() {
		return belong;
	}
	public void setBelong(Integer belong) {
		this.belong = belong;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getArticleId() {
		return articleId;
	}
	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSequence() {
		return sequence;
	}
	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}
	
}
