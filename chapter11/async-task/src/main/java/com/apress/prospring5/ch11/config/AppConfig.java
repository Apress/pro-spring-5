package com.apress.prospring5.ch11.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Configuration
@EnableAsync
@ComponentScan(basePackages  = {"com.apress.prospring5.ch11"} )
public class AppConfig {
}
