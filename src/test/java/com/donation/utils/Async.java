package com.donation.utils;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.donation.config.TaskExecutorConfig;

import com.donation.service.async.AsyncTaskService;

public class Async {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(TaskExecutorConfig.class);

        AsyncTaskService asyncTaskService = (AsyncTaskService) context.getBean(AsyncTaskService.class);

        for (int i = 0; i < 10; i++) {

            //asyncTaskService.executeAsyncTask(i);
        }
        context.close();
    }
}
