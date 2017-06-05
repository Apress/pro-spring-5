package com.apress.prospring5.ch11;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ScheduleTaskDemo {

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/task-namespace-app-context.xml");
        ctx.refresh();

        while (true) {
        }
    }
}
