package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.JpaConfig;
import com.apress.prospring5.ch8.Album;
import com.apress.prospring5.ch8.Instrument;
import com.apress.prospring5.ch8.Singer;
import com.apress.prospring5.ch8.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by iuliana.cosmina on 4/29/17.
 */
public class SingerJPATest {
	private static Logger logger = LoggerFactory.getLogger(SingerJPATest.class);

	private GenericApplicationContext ctx;
	private SingerService singerService;

	@Before
	public void setUp(){
		ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		singerService = ctx.getBean("jpaSingerService", SingerService.class);
		assertNotNull(singerService);
	}

	@Test
	public void tesFindByCriteriaQuery(){
		List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
		assertEquals(1, singers.size());
		listSingersWithAlbum(singers);
	}


	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info(" ---- Listing singers with instruments:");
		for (Singer singer : singers) {
			logger.info(singer.toString());
			if (singer.getAlbums() != null) {
				for (Album album :
						singer.getAlbums()) {
					logger.info("\t" + album.toString());
				}
			}
			if (singer.getInstruments() != null) {
				for (Instrument instrument : singer.getInstruments()) {
					logger.info("\tInstrument: " + instrument.getInstrumentId());
				}
			}
		}
	}


	@After
	public void tearDown(){
		ctx.close();
	}

}
