package com.apress.prospring5.ch6;

import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcContactDaoSample {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        System.out.println("Last name for contact id 1 is: " +
                contactDao.findLastNameById(1l));
    }
}
