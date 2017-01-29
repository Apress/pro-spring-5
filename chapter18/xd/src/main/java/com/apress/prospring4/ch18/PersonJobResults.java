package com.apress.prospring4.ch18;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class PersonJobResults {
    public static void main(String[] args) {
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("/META-INF/spring/config/applicationContext.xml");

        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);

        List<Map<String, Object>> results = jdbcTemplate.queryForList("select * from people");

        for (Map<String, Object> result : results) {
            System.out.println(result);
        }
    }
}
