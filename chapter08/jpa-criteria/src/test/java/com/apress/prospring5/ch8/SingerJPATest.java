package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.JpaConfig;
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
	public void setUp() {
		ctx = new AnnotationConfigApplicationContext(JpaConfig.class);
		singerService = ctx.getBean("jpaSingerService", SingerService.class);
		assertNotNull(singerService);
	}

	@Test
	public void tesFindByCriteriaQuery() {
		List<Singer> singers = singerService.findByCriteriaQuery("John", "Mayer");
		assertEquals(1, singers.size());
		listSingersWithAlbum(singers);
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

	@After
	public void tearDown() {
		ctx.close();
	}
}
