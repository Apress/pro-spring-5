package com.apress.prospring4.ch11;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ScheduleTaskAnnotationSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/task-annotation-app-context.xml");
        ctx.refresh();

        while (true) {
        }
    }
} 
