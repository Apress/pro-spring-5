package com.apress.prospring5.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/app-context-xml.xml");
        ctx.refresh();

        Documentarist documentaristOne = ctx.getBean("documentaristOne", Documentarist.class);
        Documentarist documentaristTwo = ctx.getBean("documentaristTwo", Documentarist.class);

        System.out.println("Documentarist One >>");
        documentaristOne.execute();

        System.out.println("\nDocumentarist Two >> ");
        documentaristTwo.execute();
    }
}
