package com.apress.prospring5.ch14;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class RuleEngineDemo {
    private static Logger logger = LoggerFactory.getLogger(RuleEngineDemo.class);

    public static void main(String... args) throws Exception {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context.xml");
        // remove "rules" from the line marked with (*) from build.gradle and decomment following line to use
        // inline bean declaration
        //ctx.load("classpath:spring/app-context-inline.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean("singerService", SingerService.class);

        Singer singer = new Singer();
        singer.setId(1l);
        singer.setFirstName("John");
        singer.setLastName("Mayer");
        singer.setBirthDate(
                new DateTime(1977, 10, 16, 0, 0, 0, 0));
        singerService.applyRule(singer);
        logger.info("Singer: " + singer);

        System.in.read();

        singerService.applyRule(singer);
        logger.info("Singer: " + singer);

        ctx.close();
    }
}
