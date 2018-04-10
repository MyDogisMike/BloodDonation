package com.donation.utils;

import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class SendEmailUtil{
	@Autowired
	private EmailConfig emailConfig;	//用这种方式自动注入时不能加static修饰词，不然值一直为null，具体原因暂不清楚
	
	@Autowired
	private JavaMailSender mailSender;
	
	public static SendEmailUtil sendEmailUtil;	//单多个用户同时使用这个对象时会不会造成线程不安全
	
	@PostConstruct
    public void init() {
		sendEmailUtil = this;
    }
	
	
	
	public String sendSimpleMail(String to, String subject, String content) {
		
		MimeMessage mimessage = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(mimessage,true);
			//System.out.println(emailConfig.getEmailFrom());
			helper.setFrom(emailConfig.getEmailFrom());
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//System.out.println("发送邮件："+subject+"至"+to);
		
		mailSender.send(mimessage);
		return "success";
	}
	
	
	public void sendEmails(String[] tos, String subject, String content){
		for (int i = 0; i < tos.length; i++){
			sendSimpleMail(tos[i], subject, content);
		}
	}
	
}
