package com.donation.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.donation.entity.ArticleComment;
import com.donation.vo.ArticleCommentVO;

@Mapper
public interface ArticleCommentMapper extends tk.mybatis.mapper.common.Mapper<ArticleComment> {
	public List<ArticleCommentVO> getCommentListByArticleId(Long articleId);
}
