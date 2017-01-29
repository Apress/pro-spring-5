package com.apress.prospring4.ch12;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class HttpInvokerClientSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/http-invoker-app-context.xml");
        ctx.refresh();

        ContactService contactService =
                ctx.getBean("remoteContactService", ContactService.class);

        System.out.println("Finding all contacts");
        List<Contact> contacts = contactService.findAll();
        listContacts(contacts);

        System.out.println("Finding contact with first name equals Chris");
        contacts = contactService.findByFirstName("Chris");
        listContacts(contacts);
    }

    private static void listContacts(List<Contact> contacts) {
        for (Contact contact: contacts) {
            System.out.println(contact);
        }

        System.out.println("");
    }
}
