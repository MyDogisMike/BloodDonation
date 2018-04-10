package com.donation.service;

import java.util.Map;

import com.donation.entity.Activity;
import com.donation.query.ArticlePageQuery;
import com.donation.query.LayUIQuery;
import com.donation.vo.ActivityVO;
import com.donation.vo.LayUIVO;

public interface ActivityService {
	public LayUIVO getActivityList(LayUIQuery query);
	
	public int addActivity(Activity activity);
	
	public int updateActivityById(Activity activity);
	
	public ActivityVO getActivityVOById(Long id);
	
	public Map<String, Object> getAllActivitys(ArticlePageQuery query);
	
	public Activity getActivityById(Long id);
}
