package com.apress.prospring5.ch9;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/tx-programmatic-app-context.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("contactService",
                ContactService.class);

        System.out.println("Contact count: " + contactService.countAll());

        ctx.close();
    }
}
