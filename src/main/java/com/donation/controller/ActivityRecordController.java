package com.donation.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.donation.constant.LoginVariable;
import com.donation.entity.Activity;
import com.donation.entity.ActivityRecord;
import com.donation.entity.Users;
import com.donation.service.ActivityRecordService;
import com.donation.service.ActivityService;

@Controller
@RequestMapping("/record")
public class ActivityRecordController {
	@Autowired
	private ActivityRecordService activityRecordService;
	
	@Autowired
	private ActivityService activityService;
	
	@RequestMapping("/getCountFromActivity")
	@ResponseBody
	public int getCountFromActivity(Long activityId, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		return activityRecordService.getCountFromActivity(activityId, loginUser.getId());
	}
	
	@RequestMapping("/activitIn")
	@ResponseBody
	public String activitIn(ActivityRecord record, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		record.setUserId(loginUser.getId());
		int flag = activityRecordService.activityIn(record);
		if (flag <= 0){
			return "error";
		}
		Activity activity = activityService.getActivityById(record.getActivityId());
		activity.setPersonCount(activity.getPersonCount() + 1);
		activityService.updateActivityById(activity);
		return "success";
	}
	
	@RequestMapping("/activitOut")
	@ResponseBody
	public String activitOut(ActivityRecord record, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		record.setUserId(loginUser.getId());
		int flag = activityRecordService.activityOut(record);
		if (flag <= 0){
			return "error";
		}
		Activity activity = activityService.getActivityById(record.getActivityId());
		activity.setPersonCount(activity.getPersonCount() - 1);
		activityService.updateActivityById(activity);
		return "success";
	}
	
	@RequestMapping("/uploadRecordList")
    @ResponseBody
    public String uploadRecordList(HttpServletResponse response, Long activityId){
		activityRecordService.exportRecords(activityId);
		File file = new File("D:/record.xls");
		try {
            if (file.exists()) {
            	//activityService.getActivityById(activityId);
                InputStream fis = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                response.reset();
                response.setContentType("application/octet-stream");
                Date date = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                String dateStr = sdf.format(date);
                response.addHeader("Content-Disposition",
                        "attachment; filename=" + new String(dateStr.getBytes()) + ".xls");
                response.addHeader("Content-Length", "" + file.length());
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                toClient.write(buffer);
                toClient.flush();
                
                toClient.close();
                return "success";
            } else {
                throw new IOException("找不到文件！");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
		
	}
}
