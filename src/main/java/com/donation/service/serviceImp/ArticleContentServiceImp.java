package com.donation.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.ArticleContent;
import com.donation.mapper.ArticleContentMapper;
import com.donation.service.ArticleContentService;
@Service
public class ArticleContentServiceImp implements ArticleContentService{
	@Autowired
	private ArticleContentMapper mapper;

	@Override
	public int addContent(ArticleContent content) {
		return mapper.insertSelective(content);
	}

	@Override
	public List<ArticleContent> getContentByArticleId(Long articleId) {
		return mapper.getContentByArticleIdOrderBySequence(articleId);
	}

	@Override
	public List<ArticleContent> getContentByActivityId(Long articleId) {
		return mapper.getContentByActivityIdOrderBySequence(articleId);
	}

}
