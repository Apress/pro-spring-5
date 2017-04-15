package com.apress.prospring5.ch6;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationJdbcDaoSample {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        System.out.println(contactDao.findFirstNameById(1l));
    }
}
