package com.donation.service.async;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import com.donation.utils.EmailUtil2;
import com.donation.utils.SendEmailUtil;

@Service
@EnableAsync
public class AsyncTaskService {

	@Autowired
	EmailUtil2 email;
	
    @Async
    public void executeAsyncTask(String tos, String subject, String content) {
    	System.out.println(tos);
    	//email.sendSimpleMail(tos, subject, content);
    	//System.out.println(tos);
    }

}
