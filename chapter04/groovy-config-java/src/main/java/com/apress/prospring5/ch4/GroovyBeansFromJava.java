package com.apress.prospring5.ch4;

import com.apress.prospring5.ch3.xml.Singer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericGroovyApplicationContext;

public class GroovyBeansFromJava {

	public static void main(String... args) {
		ApplicationContext context = new GenericGroovyApplicationContext("classpath:beans.groovy");
		Singer singer = context.getBean("singer", Singer.class);
		System.out.println(singer);
	}
}
