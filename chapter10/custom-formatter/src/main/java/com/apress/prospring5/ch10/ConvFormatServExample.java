package com.apress.prospring5.ch10;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

public class ConvFormatServExample {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/conv-format-service-app-context.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);

        System.out.println("Contact info: " + chris);

        ConversionService conversionService = ctx.getBean("conversionService", ConversionService.class);
        System.out.println("Birthdate of contact is : " +
                conversionService.convert(chris.getBirthDate(), String.class));
    }
}
