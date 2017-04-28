package com.apress.prospring5.ch8;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPASample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean(
            "jpaContactService", ContactService.class);
        
        List<Contact> contacts = contactService.findAllByNativeQuery();

        for(Contact contact : contacts) {
            System.out.println(contact);
        }
    }
}
