package com.apress.prospring5.ch5;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
@Configuration
@ComponentScan(basePackages = {"com.apress.prospring5.ch5"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class AppConfig {

}
