package com.donation.service.serviceImp;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.donation.entity.Activity;
import com.donation.entity.ActivityRecord;
import com.donation.mapper.ActivityMapper;
import com.donation.mapper.ActivityRecordMapper;
import com.donation.service.ActivityRecordService;
import com.donation.utils.ExcelExport;
import com.donation.vo.ActivityRecordVO;
@Service
public class ActivityRecordServiceImp implements ActivityRecordService{
	@Autowired
	private ActivityRecordMapper mapper;
	@Autowired
	private ActivityMapper activityMapper;

	@Override
	public int getCountFromActivity(Long activityId, Long userId) {
		return mapper.getCountFromActivity(activityId, userId);
	}

	@Override
	public int activityIn(ActivityRecord record) {
		
		return mapper.insertSelective(record);
	}

	@Override
	public int activityOut(ActivityRecord record) {
		
		return mapper.delete(record);
	}

	@Override
	public void exportRecords(Long activityId) {
		try {
            List<ActivityRecordVO> list = mapper.getRecordVOList(activityId);
            Activity activity = activityMapper.selectByPrimaryKey(activityId);
            String title = "《 "+activity.getTitle()+" 》 报名表";
            ExcelExport<ActivityRecordVO> excelExport = new ExcelExport<ActivityRecordVO>();
            String[] headers = { "用户名", "手机号码", "邮箱", "签到"};
           /* if(list.size()>0){*/
            FileOutputStream out = new FileOutputStream("D:/record.xls");
            List<String> l = new ArrayList<>();
            l.add("userName");
            l.add("userPhone");
            l.add("userEmail");
            l.add("");

            excelExport.exportExcel(title,headers,list, out, "yyyy-MM-dd", l);
            out.close();
           /* return 2;
            }*/
           // return 1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	

}
