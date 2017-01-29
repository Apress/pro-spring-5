package com.apress.prospring4.ch12;

import org.joda.time.DateTime;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

public class RestfulClientSample {
    private static final String URL_GET_ALL_CONTACTS =
            "http://localhost:8080/ch12/restful/contact/listdata";
    private static final String URL_GET_CONTACT_BY_ID =
            "http://localhost:8080/ch12/restful/contact/{id}";
    private static final String URL_CREATE_CONTACT =
            "http://localhost:8080/ch12/restful/contact/";
    private static final String URL_UPDATE_CONTACT =
            "http://localhost:8080/ch12/restful/contact/{id}";
    private static final String URL_DELETE_CONTACT =
            "http://localhost:8080/ch12/restful/contact/{id}";

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/restful-client-app-context.xml");
        ctx.refresh();

        Contact contact;
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);

        System.out.println("Testing retrieve all contacts:");
        Contacts contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        listContacts(contacts);

        System.out.println("Testing retrieve a contact by id :");
        contact = restTemplate.getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
        System.out.println(contact);
        System.out.println("");

        contact = restTemplate.getForObject(URL_UPDATE_CONTACT, Contact.class, 1);
        contact.setFirstName("John Doe");
        System.out.println("Testing update contact by id :");
        restTemplate.put(URL_UPDATE_CONTACT, contact, 1);
        System.out.println("Contact update successfully: " + contact);
        System.out.println("");

        restTemplate.delete(URL_DELETE_CONTACT, 1);
        System.out.println("Testing delete contact by id :");
        contacts = restTemplate.getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        listContacts(contacts);

        System.out.println("Testing create contact :");
        Contact contactNew = new Contact();
        contactNew.setFirstName("James");
        contactNew.setLastName("Gosling");
        contactNew.setBirthDate(new DateTime());
        contactNew = restTemplate.postForObject(URL_CREATE_CONTACT, contactNew, Contact.class);
        System.out.println("Contact created successfully: " + contactNew);
    }

    private static void listContacts(Contacts contacts) {
        for (Contact contact: contacts.getContacts()) {
            System.out.println(contact);
        }

        System.out.println("");
    }
}
