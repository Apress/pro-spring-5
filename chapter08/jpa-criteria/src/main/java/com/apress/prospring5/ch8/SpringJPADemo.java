package com.apress.prospring5.ch8;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class SpringJPADemo {

	private static Logger logger = LoggerFactory.getLogger(SpringJPADemo.class);

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
