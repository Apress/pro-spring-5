package com.apress.prospring5.ch4;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigXMLDemo {

	public static void main(String... args) {
		ApplicationContext ctx = new
				ClassPathXmlApplicationContext("classpath:spring/app-context-xml.xml");

			MessageRenderer renderer =
				ctx.getBean("messageRenderer", MessageRenderer.class);
		renderer.render();
	}
}
