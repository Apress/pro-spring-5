package com.apress.prospring5.ch12.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

}