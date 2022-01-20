package com.apress.prospring5.ch4;

import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch4.mixed.AppConfigFive;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaConfigExampleTwo {
    public static void main(String... args) {
        ApplicationContext ctx = new 
            //AnnotationConfigApplicationContext(AppConfigTwo.class);
            //AnnotationConfigApplicationContext(AppConfigThree.class);
            AnnotationConfigApplicationContext(AppConfigFive.class);

        MessageRenderer renderer =
            ctx.getBean("messageRenderer", MessageRenderer.class);

        renderer.render();
    }
}
