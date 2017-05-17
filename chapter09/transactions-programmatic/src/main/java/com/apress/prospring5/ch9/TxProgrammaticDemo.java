package com.apress.prospring5.ch9;

import com.apress.prospring5.ch9.services.SingerService;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TxProgrammaticDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/tx-programmatic-app-context.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);

        System.out.println("Singer count: " + singerService.countAll());

        ctx.close();
    }
}
