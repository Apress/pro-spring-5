package com.apress.prospring5.ch5;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by iuliana.cosmina on 4/9/17.
 */
public class AspectJAnnotationTest {

	@Test
	public void xmlTest() {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();

		NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
		documentarist.execute();

		ctx.close();
	}

	@Test
	public void configTest() {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
		documentarist.execute();

		ctx.close();
	}

}
