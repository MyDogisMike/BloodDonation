package com.donation.service.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.Articles;
import com.donation.mapper.ArticlesMapper;
import com.donation.query.ArticlePageQuery;
import com.donation.query.ArticleQuery;
import com.donation.query.LayUIQuery;
import com.donation.service.ArticlesService;
import com.donation.vo.ArticleVO;
import com.donation.vo.LayUIVO;
@Service
public class ArticlesServiceImp implements ArticlesService{
	@Autowired
	private ArticlesMapper mapper;

	@Override
	public int addArticle(Articles articles) {
		return mapper.insertSelective(articles);
	}

	@Override
	public List<Articles> getArticleList(ArticleQuery query) {
		
		return mapper.getArticleList(query);
	}

	@Override
	public Articles getArticleById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateArticleById(Articles article) {
		return mapper.updateByPrimaryKeySelective(article);
	}

	@Override
	public ArticleVO getArticleVOById(Long id) {
		return mapper.getArticleVOById(id);
	}

	@Override
	public List<Articles> getArticleListByPage(ArticlePageQuery query) {
		return mapper.getArticleListByPage(query);
	}

	@Override
	public Map<String, Object> getAllArticles(ArticlePageQuery query) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (query.getPage() == null || query.getPage() == 0){	//首次加载页面
			query.setSkipRows(0L);
			result.put("articles", mapper.getArticleListByPage(query));
			Long allCounts = mapper.getArticlesCount();
			result.put("allCounts", allCounts);
			double temp = (allCounts+0.0) / query.getCount();
			result.put("allPages", Math.ceil(temp));
		}else{
			query.setSkipRows((query.getPage()-1L) * query.getCount());
			result.put("articles", mapper.getArticleListByPage(query));
		}
		return result;
	}

	@Override
	public LayUIVO getArticleList(LayUIQuery query) {
		query.setSkipRows( (query.getPage()-1)*query.getLimit() );	//计算跳过数据库前多少条数据
		List<ArticleVO> list =  mapper.getArticleVOList(query);
		Long total = mapper.getArticlesCount();
		return LayUIVO.data(Integer.parseInt(total+""), list);
	}

}
