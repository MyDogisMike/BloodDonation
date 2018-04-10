package com.donation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.donation.entity.Activity;
import com.donation.query.ArticlePageQuery;
import com.donation.query.LayUIQuery;
import com.donation.vo.ActivityVO;

@Mapper
public interface ActivityMapper extends tk.mybatis.mapper.common.Mapper<Activity>{
	
	public Integer getActivityCount(@Param("status")Integer status, @Param("userId")Long userId);
	
	public List<ActivityVO> getActivityVOList(LayUIQuery query);
	
	public ActivityVO getActivityVOById(Long id);
	
	public List<Activity> getActivityListByPage(ArticlePageQuery query);
}
