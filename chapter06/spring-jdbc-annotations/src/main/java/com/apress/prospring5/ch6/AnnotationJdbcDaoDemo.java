package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationJdbcDaoDemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);

        System.out.println(singerDao.findFirstNameById(1l));
    }
}
