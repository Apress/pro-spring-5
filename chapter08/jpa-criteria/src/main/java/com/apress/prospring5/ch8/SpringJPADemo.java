package com.apress.prospring5.ch8;

import java.util.List;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringJPADemo {
    public static void main(String... args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:spring/app-context-annotation.xml");
        ctx.refresh();

        SingerService singerService = ctx.getBean(SingerService.class);
        
        List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
        listSingersWithAlbum(singers);

        ctx.close();
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        System.out.println("");
        System.out.println("Listing singers with details:");

        for (Singer singer: singers) {
            System.out.println(singer);
            if (singer.getAlbums() != null) {
                for (Album album:
                    singer.getAlbums()) {
                    System.out.println(album);
                }
            }

            if (singer.getInstruments() != null) {
                for (Instrument hobby: singer.getInstruments()) {
                    System.out.println(hobby);
                }
            }

            System.out.println();
        }
    }
}
