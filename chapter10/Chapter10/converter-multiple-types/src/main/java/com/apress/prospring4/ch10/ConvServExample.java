package com.apress.prospring4.ch10;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.convert.ConversionService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConvServExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/conv-service-app-context.xml");
        ctx.refresh();

        Contact chris = ctx.getBean("chris", Contact.class);

        System.out.println("Contact info: " + chris);

        ConversionService conversionService = ctx.getBean(ConversionService.class);

        AnotherContact anotherContact =
                conversionService.convert(chris, AnotherContact.class);
        System.out.println("Another contact info: " + anotherContact);

        String[] stringArray = conversionService.convert("a,b,c", String[].class);

        System.out.println("String array: " + stringArray[0] +
                stringArray[1] + stringArray[2]);

        List<String> listString = new ArrayList<String>();
        listString.add("a");
        listString.add("b");
        listString.add("c");

        Set<String> setString = conversionService.convert(listString, HashSet.class);

        for (String string: setString)
            System.out.println("Set: " + string);
    }
}
