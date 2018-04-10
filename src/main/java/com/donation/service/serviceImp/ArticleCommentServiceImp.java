package com.donation.service.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.ArticleComment;
import com.donation.mapper.ArticleCommentMapper;
import com.donation.service.ArticleCommentService;
import com.donation.vo.ArticleCommentVO;
@Service
public class ArticleCommentServiceImp implements ArticleCommentService{
	@Autowired
	private ArticleCommentMapper mapper;
	@Override
	public int addComment(ArticleComment comment) {
		return mapper.insertSelective(comment);
	}
	@Override
	public List<ArticleCommentVO> getCommentListByArticleId(Long articleId) {
		return mapper.getCommentListByArticleId(articleId);
	}

}
