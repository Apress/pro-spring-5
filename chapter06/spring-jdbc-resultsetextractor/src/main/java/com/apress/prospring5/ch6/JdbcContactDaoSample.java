package com.apress.prospring5.ch6;

import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcContactDaoSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();

        ContactDao contactDao = ctx.getBean("contactDao", ContactDao.class);

        List<Contact> contactsWithDetail = contactDao.findAllWithDetail();

        for (Contact contact: contactsWithDetail) {
            System.out.println(contact);

            if (contact.getContactTelDetails() != null) {
                for (ContactTelDetail contactTelDetail: contact.getContactTelDetails()) {
                    System.out.println("---" + contactTelDetail);
                }
            }

            System.out.println();
        }
    }
}
