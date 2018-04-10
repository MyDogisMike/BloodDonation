package com.donation.service;

import java.util.List;
import java.util.Map;

import com.donation.entity.Articles;
import com.donation.query.ArticlePageQuery;
import com.donation.query.ArticleQuery;
import com.donation.query.LayUIQuery;
import com.donation.vo.ArticleVO;
import com.donation.vo.LayUIVO;

public interface ArticlesService {
	public int addArticle(Articles articles);
	
	public List<Articles> getArticleList(ArticleQuery query);
	
	public Articles getArticleById(Long id);
	
	public int updateArticleById(Articles article);
	
	public ArticleVO getArticleVOById(Long id);
	
	public List<Articles> getArticleListByPage(ArticlePageQuery query);
	
	public Map<String, Object> getAllArticles(ArticlePageQuery query);
	
	public LayUIVO getArticleList(LayUIQuery query);
	
}
