package com.apress.prospring5.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Comment or de-comment lines 11-13 according to examples in the book
 */
public class AopNamespaceDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        //ctx.load("classpath:spring/app-context-xml-01.xml");
        //ctx.load("classpath:spring/app-context-xml-02.xml");
        ctx.load("classpath:spring/app-context-xml-03.xml");
        ctx.refresh();

        NewDocumentarist documentarist = ctx.getBean("documentarist", NewDocumentarist.class);
        documentarist.execute();

        ctx.close();
    }
}
