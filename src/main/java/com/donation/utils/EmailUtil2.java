package com.donation.utils;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.donation.vo.Email;

@Component
public class EmailUtil2 {
	@Autowired
	private EmailConfig emailConfig; // 用这种方式自动注入时不能加static修饰词，不然值一直为null，具体原因暂不清楚

	@Autowired
	private JavaMailSender mailSender;

	private StringBuffer message = new StringBuffer();

	@Autowired
	TaskExecutor taskExecutor;
	//@Async
	public void sendMail(Email email) throws MessagingException, IOException {
		if (email.getAddress() == null || email.getAddress().length == 0) {
			this.message.append("没有收件人");
			return;
		}
		if (email.getAddress().length > 1) {// 收件人大于5封时，采用异步发送
			sendMailByAsynchronousMode(email);
			//System.out.println("正在发送邮件");
			this.message.append(email.getAddress().length+"封邮件，正在采用异步方式发送...<br/>");
		} else {
			sendMailBySynchronizationMode(email);
			this.message.append("正在同步方式发送邮件...<br/>");
		}
	}

	/**
	 * 异步发送
	 */
	
	public void sendMailByAsynchronousMode(final Email email) {
		//System.out.println("===begin");
//		try {
//			sendMailBySynchronizationMode(email);
//		} catch (Exception e) {
//			
//		}
		
		taskExecutor.execute(new Runnable() {
			public void run() {
				try {
					sendMailBySynchronizationMode(email);
				} catch (Exception e) {
					
				}
			}
		});
		//System.out.println("===end");
	}

	/**
	 * 同步发送
	 * 
	 * @throws IOException
	 * @see com.zhangjihao.service.MailServiceMode#sendMail(com.zhangjihao.bean.Email)
	 */
	public void sendMailBySynchronizationMode(Email email) throws MessagingException, IOException {
		MimeMessage mime = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mime, true, "utf-8");
		helper.setFrom(emailConfig.getEmailFrom());// 发件人
		helper.setTo(email.getAddress());// 收件人
		helper.setSubject(email.getSubject());// 邮件主题
		helper.setText(email.getContent(), true);// true表示设定html格式
		mailSender.send(mime);
	}

	public StringBuffer getMessage() {
		return message;
	}

	public void setMessage(StringBuffer message) {
		this.message = message;
	}

}
