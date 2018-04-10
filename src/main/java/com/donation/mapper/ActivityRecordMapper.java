package com.donation.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.donation.entity.ActivityRecord;
import com.donation.vo.ActivityRecordVO;

@Mapper
public interface ActivityRecordMapper extends tk.mybatis.mapper.common.Mapper<ActivityRecord>{
	public int getCountFromActivity(@Param("activityId")Long activityId, @Param("userId")Long userId);
	
	public List<ActivityRecordVO> getRecordVOList(Long activityId);
}
