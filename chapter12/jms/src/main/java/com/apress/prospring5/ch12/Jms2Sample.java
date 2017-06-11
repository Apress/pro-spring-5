package com.apress.prospring5.ch12;

import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.Arrays;

public class Jms2Sample {
    public static void main(String... args) throws Exception{
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/jms-common.xml", "classpath:spring/jms-sender-app-context.xml",
                "classpath:spring/jms-listener-app-context.xml");
        ctx.refresh();

        MessageSender messageSender = ctx.getBean("messageSender", MessageSender.class);
        Arrays.toString(ctx.getBeanDefinitionNames());

        for(int i=0; i < 10; ++i) {
            messageSender.sendMessage("Test message: " + i);
        }

        System.in.read();
        ctx.close();
    }
}
