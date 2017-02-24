package com.apress.prospring5.ch3.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by iuliana.cosmina on 2/25/17.
 */
public class InheritanceDemo {

	public static void main(String... args) {
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
		ctx.load("classpath:spring/app-context-xml.xml");
		ctx.refresh();

		Singer parent = (Singer) ctx.getBean("parent");
		Singer child = (Singer) ctx.getBean("child");

		System.out.println("Parent:\n" + parent);
		System.out.println("Child:\n" + child);
	}
}
//abstract="true"
