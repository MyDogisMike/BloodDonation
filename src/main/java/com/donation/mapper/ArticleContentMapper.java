package com.donation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.donation.entity.ArticleContent;

@Mapper
public interface ArticleContentMapper extends tk.mybatis.mapper.common.Mapper<ArticleContent>{
	public List<ArticleContent> getContentByArticleIdOrderBySequence(Long articleId);
	
	public List<ArticleContent> getContentByActivityIdOrderBySequence(Long articleId);
}
