package com.apress.prospring5.ch7;

import java.util.List;

import com.apress.prospring5.ch7.config.AppConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringHibernateSample {
    private static Logger logger = LoggerFactory.getLogger(SpringHibernateSample.class);


    public static void main(String... args) {
        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        SingerDao singerDao = ctx.getBean(SingerDao.class);

        Singer singer = singerDao.findById(2l);

        singerDao.delete(singer);

        listSingersWithAlbum(singerDao.findAllWithAlbum());

        ctx.close();
    }

    private static void listSingersWithAlbum(List<Singer> singers) {
        logger.info(" ---- Listing singers with instruments:");

        for (Singer singer: singers) {
            logger.info(singer.toString());

            if (singer.getAlbums() != null) {
                for (Album album:
                         singer.getAlbums()) {
                    logger.info("\t" +album.toString());
                }
            }

            if (singer.getInstruments() != null) {
                for (Instrument instrument: singer.getInstruments()) {
                    logger.info("\t" + instrument.getInstrumentId());
                }
            }

        }
    }
}
