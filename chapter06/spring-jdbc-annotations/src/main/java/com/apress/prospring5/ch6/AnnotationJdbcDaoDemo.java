package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AnnotationJdbcDaoDemo {
    private static Logger logger = LoggerFactory.getLogger(AnnotationJdbcDaoDemo.class);

    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        SingerDao singerDao = ctx.getBean("singerDao", SingerDao.class);

       logger.info(singerDao.findFirstNameById(1l));
    }
}
