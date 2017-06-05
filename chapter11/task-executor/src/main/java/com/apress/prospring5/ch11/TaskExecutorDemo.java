package com.apress.prospring5.ch11;

import com.apress.prospring5.ch11.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class TaskExecutorDemo {

	public static void main(String... args) throws Exception {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		TaskToExecute taskToExecute = ctx.getBean(TaskToExecute.class);
		taskToExecute.executeTask();

		System.in.read();
		ctx.close();
	}
}
