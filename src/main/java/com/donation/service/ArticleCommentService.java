package com.donation.service;

import java.util.List;

import com.donation.entity.ArticleComment;
import com.donation.vo.ArticleCommentVO;

public interface ArticleCommentService {
	public int addComment(ArticleComment comment);
	
	public List<ArticleCommentVO> getCommentListByArticleId(Long articleId);
}
