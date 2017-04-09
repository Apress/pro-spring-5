package com.apress.prospring5.ch5.config;

import com.apress.prospring5.ch2.common.Contact;
import com.apress.prospring5.ch5.introduction.IsModifiedAdvisor;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
@Configuration
public class AppConfig {

	@Bean
	public Contact guitarist() {
		Contact guitarist = new Contact();
		guitarist.setName("John Mayer");
		return guitarist;
	}

	@Bean
	public Advisor advisor() {
		return new IsModifiedAdvisor();
	}

	@Bean ProxyFactoryBean bean() {
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(guitarist());
		proxyFactoryBean.setProxyTargetClass(true);
		proxyFactoryBean.addAdvisor(advisor());
		return proxyFactoryBean;
	}
}
