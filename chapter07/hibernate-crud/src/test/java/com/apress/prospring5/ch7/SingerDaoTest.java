package com.apress.prospring5.ch7;

import com.apress.prospring5.ch7.config.AdvancedConfig;
import com.apress.prospring5.ch7.dao.SingerDao;
import com.apress.prospring5.ch7.entities.Album;
import com.apress.prospring5.ch7.entities.Instrument;
import com.apress.prospring5.ch7.entities.Singer;
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
 * Created by iuliana.cosmina on 4/22/17.
 */
public class SingerDaoTest {
	private static Logger logger = LoggerFactory.getLogger(SingerDaoTest.class);

	private GenericApplicationContext ctx;
	private SingerDao singerDao;

	@Before
	public void setUp(){
		 ctx = new AnnotationConfigApplicationContext(AdvancedConfig.class);
		 singerDao = ctx.getBean(SingerDao.class);
		 assertNotNull(singerDao);
	}

	@Test
	public void testFindAll(){
		List<Singer> singers = singerDao.findAll();
		assertEquals(3, singers.size());
		listSingers(singers);
	}

	@Test
	public void testFindAllWithAlbum(){
		List<Singer> singers = singerDao.findAllWithAlbum();
		assertEquals(3, singers.size());
		listSingersWithAlbum(singers);
	}

	@Test
	public void testFindByID(){
		Singer  singer = singerDao.findById(1L);
		assertNotNull(singer);
		logger.info(singer.toString());
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

		singerDao.save(singer);
		assertNotNull(singer.getId());

		List<Singer> singers = singerDao.findAllWithAlbum();
		assertEquals(4, singers.size());
		listSingersWithAlbum(singers);
	}

	@Test
	public void testUpdate(){
		Singer singer = singerDao.findById(1L);
		//making sure such singer exists
		assertNotNull(singer);
		//making sure we got expected record
		assertEquals("Mayer", singer.getLastName());
		//retrieve the album
		Album album = singer.getAlbums().stream().filter(a -> a.getTitle().equals("Battle Studies")).findFirst().get();

		singer.setFirstName("John Clayton");
		singer.removeAlbum(album);
		singerDao.save(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());
	}

	@Test
	public void testDelete(){
		Singer singer = singerDao.findById(4l);
		//making sure such singer exists
		assertNotNull(singer);
		singerDao.delete(singer);

		listSingersWithAlbum(singerDao.findAllWithAlbum());
	}


	private static void listSingers(List<Singer> singers) {
		logger.info(" ---- Listing singers:");
		for (Singer singer : singers) {
			logger.info(singer.toString());
		}
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
	public void tearDown(){
		ctx.close();
	}

}
