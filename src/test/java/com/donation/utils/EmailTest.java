package com.donation.utils;	//Springboot的测试类一定要放到与源代码一样的包下面才能初始化成功

import java.io.IOException;

import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

import com.donation.service.async.AsyncTaskService;
import com.donation.vo.Email;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAsync
public class EmailTest {
	@Autowired
	AsyncTaskService service;
	@Autowired
	SendEmailUtil sendEmail;
	@Autowired
	EmailUtil2 semail;

	@Test
	public void test(){
		Email email = new Email();
		email.setAddressee("2238883940@qq.com;1147543780@qq.com");
		email.setContent("广州地区现急需O型血，请广大爱心人士积极参与献血活动");
		email.setSubject("献血通知");
		try {
			semail.sendMail(email);
			System.out.println("000000000"+semail.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//service.executeAsyncTask("2238883940@qq.com", "献血通知", "广州地区现急需O型血，请广大爱心人士积极参与献血活动");
		try {
			Thread.sleep(100000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("just do it!");
	}
}
