package com.apress.prospring5.ch3.annotated;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * Created by iuliana.cosmina on 2/22/17.
 */
@Component("johnMayer")
@DependsOn("gopher")
public class Singer implements ApplicationContextAware{

	ApplicationContext applicationContext;

	@Override public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	private Guitar guitar;

	public Singer(){
	}

	public void sing() {
		guitar =  applicationContext.getBean("gopher", Guitar.class);
		guitar.sing();
	}
}
