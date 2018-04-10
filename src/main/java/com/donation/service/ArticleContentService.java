package com.donation.service;

import java.util.List;

import com.donation.entity.ArticleContent;

public interface ArticleContentService {
	public int addContent(ArticleContent content);
	
	public List<ArticleContent> getContentByArticleId(Long articleId);
	
	public List<ArticleContent> getContentByActivityId(Long articleId);
}
