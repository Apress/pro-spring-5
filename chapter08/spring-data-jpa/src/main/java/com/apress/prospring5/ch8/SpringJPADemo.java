package com.apress.prospring5.ch8;

import java.util.List;

import com.apress.prospring5.ch8.entities.Singer;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPADemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(
            "springJpaSingerService", SingerService.class);

        listSingers("Find all:", singerService.findAll());
        listSingers("Find by first name:", singerService.findByFirstName("John"));
        listSingers("Find by first and last name:",   
            singerService.findByFirstNameAndLastName("John", "Mayer"));
    }

    private static void listSingers(String message, List<Singer> singers) {
        System.out.println("");
        System.out.println(message);
        for (Singer singer: singers) {
            System.out.println(singer);
            System.out.println();
        }
    }
}
