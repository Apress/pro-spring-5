package com.apress.prospring5.ch12.config;

import com.apress.prospring5.ch12.services.SingerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Configuration
public class RmiClientConfig {

	@Bean
	public SingerService singerService() {
		HttpInvokerProxyFactoryBean factoryBean = new HttpInvokerProxyFactoryBean();
		factoryBean.setServiceInterface(SingerService.class);
		factoryBean.setServiceUrl("http://localhost:8080/invoker/httpInvoker/singerService");
		factoryBean.afterPropertiesSet();
		return (SingerService) factoryBean.getObject();
	}
}
