package com.apress.prospring4.ch5;

import org.springframework.context.support.GenericXmlApplicationContext;

public class ProxyFactoryBeanExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();

        MyBean bean1 = (MyBean)ctx.getBean("myBean1");
        MyBean bean2 = (MyBean)ctx.getBean("myBean2");

        System.out.println("Bean 1");
        bean1.execute();

        System.out.println("\nBean 2");
        bean2.execute();
    }
}
