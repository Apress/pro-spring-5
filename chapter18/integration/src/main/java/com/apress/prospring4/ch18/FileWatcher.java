package com.apress.prospring4.ch18;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class FileWatcher {
    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext
                = new ClassPathXmlApplicationContext("/META-INF/spring/jobs/personJob/personJob.xml");
    }
}
