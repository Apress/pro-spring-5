package com.apress.prospring5.ch9;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxJtaDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/tx-jta-app-context.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("contactService",
                ContactService.class);

        Contact contact = new Contact();
        contact.setFirstName("Jta");
        contact.setLastName("Manager");
        contactService.save(contact);
        System.out.println("Contact saved successfully");

        ctx.close();
    }
} 
