package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class JdbcSingerDaoDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/embedded-h2-cfg.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean(SingerDao.class);

        System.out.println("First name for singer id 1 is: " +
                singerDao.findFirstNameById(1l));

        ctx.close();
    }
}
