package com.donation.service;

import com.donation.entity.ActivityRecord;

public interface ActivityRecordService {
	public int getCountFromActivity(Long activityId, Long userId);
	
	public int activityIn(ActivityRecord record);
	
	public int activityOut(ActivityRecord record);
	
	public void exportRecords(Long activityId);
}
