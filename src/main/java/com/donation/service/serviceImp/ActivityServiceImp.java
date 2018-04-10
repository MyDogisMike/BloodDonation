package com.donation.service.serviceImp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.Activity;
import com.donation.mapper.ActivityMapper;
import com.donation.query.ArticlePageQuery;
import com.donation.query.LayUIQuery;
import com.donation.service.ActivityService;
import com.donation.vo.ActivityVO;
import com.donation.vo.LayUIVO;
@Service
public class ActivityServiceImp implements ActivityService{
	@Autowired
	private ActivityMapper mapper;
	@Override
	public LayUIVO getActivityList(LayUIQuery query) {
		query.setSkipRows( (query.getPage()-1)*query.getLimit() );	//计算跳过数据库前多少条数据
		List<ActivityVO> list =  mapper.getActivityVOList(query);
		Integer total = mapper.getActivityCount(null, null);
		return LayUIVO.data(total, list);
	}
	@Override
	public int addActivity(Activity activity) {
		return mapper.insertSelective(activity);
	}
	@Override
	public int updateActivityById(Activity activity) {
		return mapper.updateByPrimaryKeySelective(activity);
	}
	@Override
	public ActivityVO getActivityVOById(Long id) {
		
		return mapper.getActivityVOById(id);
	}
	@Override
	public Map<String, Object> getAllActivitys(ArticlePageQuery query) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (query.getPage() == null || query.getPage() == 0){	//首次加载页面
			query.setSkipRows(0L);
			result.put("articles", mapper.getActivityListByPage(query));
			Integer allCounts = mapper.getActivityCount(1, null);
			result.put("allCounts", allCounts);
			double temp = (allCounts+0.0) / query.getCount();
			result.put("allPages", Math.ceil(temp));
		}else{
			query.setSkipRows((query.getPage()-1L) * query.getCount());
			result.put("articles", mapper.getActivityListByPage(query));
		}
		return result;
	}
	@Override
	public Activity getActivityById(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

}
