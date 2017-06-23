package com.apress.prosring5.ch12.test;

import com.apress.prospring5.ch12.Singers;
import com.apress.prospring5.ch12.entities.Singer;
import com.apress.prosring5.ch12.RestClientConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 6/17/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

	final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
	private static final String URL_GET_ALL_SINGERS = "http://localhost:8080/rest/singer/listdata";
	private static final String URL_GET_SINGER_BY_ID = "http://localhost:8080/rest/singer/{id}";
	private static final String URL_CREATE_SINGER = "http://localhost:8080/rest/singer/";
	private static final String URL_UPDATE_SINGER = "http://localhost:8080/rest/singer/{id}";
	private static final String URL_DELETE_SINGER = "http://localhost:8080/rest/singer/{id}";

	@Autowired RestTemplate restTemplate;

	@Before
	public void setUp() {
		assertNotNull(restTemplate);
	}

	@Test
	public void testFindAll() {
		logger.info("--> Testing retrieve all singers");
		Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
		assertTrue(singers.getSingers().size() == 3);
		listSingers(singers);
	}

	@Test
	public void testFindbyId() {
		logger.info("--> Testing retrieve a singer by id : 1");
		Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
		assertNotNull(singer);
		logger.info(singer.toString());
	}

	@Test
	public void testUpdate() {
		logger.info("--> Testing update singer by id : 1");
		Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
		singer.setFirstName("John Clayton");
		restTemplate.put(URL_UPDATE_SINGER, singer, 1);
		logger.info("Singer update successfully: " + singer);
	}

	@Test
	public void testDelete() {
		logger.info("--> Testing delete singer by id : 3");
		restTemplate.delete(URL_DELETE_SINGER, 3);
		Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
		Boolean found = false;
		for(Singer s: singers.getSingers()) {
			if(s.getId() == 3) {
				found = true;
			}
		};
		assertFalse(found);
		listSingers(singers);
	}

	@Test
	public void testCreate() {
		logger.info("--> Testing create singer");
		Singer singerNew = new Singer();
		singerNew.setFirstName("BB");
		singerNew.setLastName("King");
		singerNew.setBirthDate(new Date(
				(new GregorianCalendar(1940, 8, 16)).getTime().getTime()));
		singerNew = restTemplate.postForObject(URL_CREATE_SINGER, singerNew, Singer.class);

		logger.info("Singer created successfully: " + singerNew);

		Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
		listSingers(singers);
	}



	private void listSingers(Singers singers) {
		singers.getSingers().forEach(s -> logger.info(s.toString()));
	}
}
