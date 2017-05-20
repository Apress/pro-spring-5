package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.config.JpaConfig;
import com.apress.prospring5.ch8.entities.Album;
import com.apress.prospring5.ch8.entities.Instrument;
import com.apress.prospring5.ch8.entities.Singer;
import com.apress.prospring5.ch8.service.SingerService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
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
		singerService = ctx.getBean(SingerService.class);
		assertNotNull(singerService);
	}

	@Test
	public void testFindAll(){
		List<Singer> singers = singerService.findAll();
		assertEquals(3, singers.size());
		listSingers(singers);
	}

	@Test
	public void testFindAllWithAlbum(){
		List<Singer> singers = singerService.findAllWithAlbum();
		assertEquals(3, singers.size());
		listSingersWithAlbum(singers);
	}

	private static void listSingers(List<Singer> singers) {
		logger.info(" ---- Listing singers:");
		singers.forEach(s -> logger.info(s.toString()));
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

	@Test
	public void testInsert(){
		Singer singer = new Singer();
		singer.setFirstName("BB");
		singer.setLastName("King");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1940, 8, 16)).getTime().getTime()));

		Album album = new Album();
		album.setTitle("My Kind of Blues");
		album.setReleaseDate(new java.sql.Date(
				(new GregorianCalendar(1961, 7, 18)).getTime().getTime()));
		singer.addAbum(album);

		album = new Album();
		album.setTitle("A Heart Full of Blues");
		album.setReleaseDate(new java.sql.Date(
				(new GregorianCalendar(1962, 3, 20)).getTime().getTime()));
		singer.addAbum(album);

		singerService.save(singer);
		assertNotNull(singer.getId());

		List<Singer> singers = singerService.findAllWithAlbum();
		assertEquals(4, singers.size());
		listSingersWithAlbum(singers);
	}

	@Test
	public void testUpdate(){
		Singer singer = singerService.findById(1L);
		//making sure such singer exists
		assertNotNull(singer);
		//making sure we got expected record
		assertEquals("Mayer", singer.getLastName());
		//retrieve the album
		Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();

		singer.setFirstName("John Clayton");
		singer.removeAlbum(album);
		singerService.save(singer);

		listSingersWithAlbum(singerService.findAllWithAlbum());
	}


	@Test
	public void testDelete(){
		Singer singer = singerService.findById(2l);
		//making sure such singer exists
		assertNotNull(singer);
		singerService.delete(singer);

		listSingersWithAlbum(singerService.findAllWithAlbum());
	}


	@Test
	public void testFindById(){
		Singer singer = singerService.findById(1L);
		assertNotNull(singer);
		assertEquals("Mayer", singer.getLastName());
		logger.info(singer.toString());
	}

	@After
	public void tearDown(){
		ctx.close();
	}

}
