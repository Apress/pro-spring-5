package com.apress.prospring4.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AspectJExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load(new String[] {"classpath:META-INF/spring/app-context-xml.xml"});
        ctx.refresh();

        MessageWriter writer = new MessageWriter();
        writer.writeMessage();
        writer.foo();
    }
}
