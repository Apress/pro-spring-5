package com.apress.prospring5.ch11.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.DefaultManagedTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Configuration
@Import({DataServiceConfig.class})
@EnableScheduling
public class AppConfig {

	// decomment this to use the TaskScheduler explicitly declared bean
	/*
	@Bean TaskScheduler carScheduler() {
		ThreadPoolTaskScheduler carScheduler = new ThreadPoolTaskScheduler();
		carScheduler.setPoolSize(10);
		return carScheduler;
	}
	*/
}
