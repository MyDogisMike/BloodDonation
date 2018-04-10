package com.donation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.donation.entity.Articles;
import com.donation.query.ArticlePageQuery;
import com.donation.query.ArticleQuery;
import com.donation.query.LayUIQuery;
import com.donation.vo.ArticleVO;

@Mapper
public interface ArticlesMapper extends tk.mybatis.mapper.common.Mapper<Articles>{
	public List<Articles> getArticleList(ArticleQuery query);
	
	public ArticleVO getArticleVOById(Long id);
	
	public List<Articles> getArticleListByPage(ArticlePageQuery query);
	
	public Long getArticlesCount();
	
	public List<ArticleVO> getArticleVOList(LayUIQuery query);
}
