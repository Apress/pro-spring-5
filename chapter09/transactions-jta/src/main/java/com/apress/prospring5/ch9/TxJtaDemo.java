package com.apress.prospring5.ch9;

import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.services.SingerService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxJtaDemo {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/tx-jta-app-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);

        Singer singer = new Singer();
        singer.setFirstName("Jta");
        singer.setLastName("Manager");
        singerService.save(singer);
        System.out.println("Singer saved successfully");

        ctx.close();
    }
} 
