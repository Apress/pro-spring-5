package com.apress.prospring4.ch4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JavaConfigXMLExample {
    public static void main(String[] args) {
        ApplicationContext ctx = new 
            ClassPathXmlApplicationContext("classpath:META-INF/spring/app-context-xml.xml");

        MessageRenderer renderer = 
            ctx.getBean("messageRenderer", MessageRenderer.class);

        renderer.render();
    }
}
