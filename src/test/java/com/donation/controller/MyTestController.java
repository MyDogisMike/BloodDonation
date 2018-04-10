package com.donation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.donation.config.TaskExecutorConfig;
import com.donation.entity.Articles;
import com.donation.mapper.ArticlesMapper;
import com.donation.query.ArticleQuery;
import com.donation.service.UsersService;
import com.donation.service.async.AsyncTaskService;
import com.donation.utils.SendEmailUtil;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MyTestController {
	@Autowired
	private UsersService service;
	
	@Autowired
	private ArticlesMapper mapper;
	
	@Autowired
	private AsyncTaskService asyncTaskService;
	
	@Test
	public void test(){
		AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
		 String[] test = {"1147543780@qq.com", "2238883940@qq.com"};
		 
		//asyncTaskService.executeAsyncTask(test, "请求献血通知", "现在广州地区急需O型血，请广大爱心人士积极献血，捐献可再生的血液，挽救不可再生生命。");
        context.close();
        
	}
	
	@Test
	public void test1(){
		Map<String, Object> param = new HashMap<String, Object>();
		Integer[] bloodType = {0, 1, 2, 3, 4};
		param.put("area", "广东省广州市%");
		param.put("bloodType", bloodType);
		String[] list = service.getEmailsByAreaAndType(param);
		String t = StringUtils.join(list, ";");
		System.out.println(t);
		for (String type : list){
			System.out.println(type);
		}
	}
}
