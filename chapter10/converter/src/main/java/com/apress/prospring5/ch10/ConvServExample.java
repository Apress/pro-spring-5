package com.apress.prospring5.ch10;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ConvServExample {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/conv-service-app-context.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);

        System.out.println("Contact info: " + chris);
    }
}
