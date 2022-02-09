package com.apress.prospring5.ch7.base;

import com.apress.prospring5.ch7.base.config.AppConfig;
import com.apress.prospring5.ch7.base.dao.SingerDao;
import com.apress.prospring5.ch7.base.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

public class SpringHibernateDemo {

	private static Logger logger = LoggerFactory.getLogger(SpringHibernateDemo.class);

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		SingerDao singerDao = ctx.getBean(SingerDao.class);
		Singer singer = singerDao.findById(2l);
		logger.info(singer.toString());

		singerDao.delete(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());
		ctx.close();
	}

	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info(" ---- Listing singers with instruments:");
		singers.forEach(s -> {
			logger.info(s.toString());
			if (s.getAlbums() != null) {
				s.getAlbums().forEach(a -> logger.info("\t" + a.toString()));
			}
			if (s.getInstruments() != null) {
				s.getInstruments().forEach(i -> logger.info("\tInstrument: " + i.getInstrumentId()));
			}
		});
	}
}
