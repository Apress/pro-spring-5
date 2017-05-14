package com.apress.prospring5.ch9;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class TxDeclarativeDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/tx-declarative-app-context.xml");
        ctx.refresh();

        ContactService contactService = ctx.getBean("contactService",
                ContactService.class);

        List<Contact> contacts = contactService.findAll();

        for (Contact contactTemp: contacts) {
            System.out.println(contactTemp);
        }

        Contact contact = contactService.findById(1L);
        contact.setFirstName("Peter");
        contactService.save(contact);
        System.out.println("Contact saved successfully: " + contact);
        System.out.println("Contact count: " + contactService.countAll());

        ctx.close();
    }
}
