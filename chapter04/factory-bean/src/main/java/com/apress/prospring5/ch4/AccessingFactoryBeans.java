package com.apress.prospring5.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.security.MessageDigest;

public class AccessingFactoryBeans {

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();
		ctx.getBean("shaDigest", MessageDigest.class);

		MessageDigestFactoryBean factoryBean =
				(MessageDigestFactoryBean) ctx.getBean("&shaDigest");
		try {
			MessageDigest shaDigest = factoryBean.getObject();
			System.out.println(shaDigest.digest("Hello world".getBytes()));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		ctx.close();
	}
}
