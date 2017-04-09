package com.apress.prospring5.ch5;

import com.apress.prospring5.ch2.common.Contact;
import com.apress.prospring5.ch5.config.AppConfig;
import com.apress.prospring5.ch5.introduction.IsModified;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class IntroductionConfigDemo {
    public static void main(String... args) {
        /*GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-xml.xml");
        ctx.refresh();*/
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        Contact bean = (Contact) ctx.getBean("bean");
        IsModified mod = (IsModified) bean;

        System.out.println("Is Contact?: " + (bean instanceof Contact));
        System.out.println("Is IsModified?: " + (bean instanceof IsModified));

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("John Mayer");

        System.out.println("Has been modified?: " + mod.isModified());
        bean.setName("Eric Clapton");

        System.out.println("Has been modified?: " + mod.isModified());
    }
}
