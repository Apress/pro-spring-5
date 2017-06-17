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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by iuliana.cosmina on 6/17/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestClientConfig.class})
public class RestClientTest {

	final Logger logger = LoggerFactory.getLogger(RestClientTest.class);
	private static final String URL_GET_ALL_SINGERS =
			"http://localhost:8080/singer/listdata";
	private static final String URL_GET_SINGER_BY_ID =
			"http://localhost:8080/ch12/restful/singer/{id}";
	private static final String URL_CREATE_SINGER =
			"http://localhost:8080/ch12/restful/singer/";
	private static final String URL_UPDATE_SINGER =
			"http://localhost:8080/ch12/restful/singer/{id}";
	private static final String URL_DELETE_SINGER =
			"http://localhost:8080/ch12/restful/singer/{id}";

	@Autowired RestTemplate restTemplate;

	@Before
	public void setUp() {
		assertNotNull(restTemplate);
	}

	@Test
	public void testFindAll() {
		logger.info("Testing retrieve all singers:");
		Singers singers = restTemplate.getForObject(URL_GET_ALL_SINGERS, Singers.class);
		assertTrue(singers.getSingers().size() == 3);
		listSingers(singers);
	}

	@Test
	public void testFindbyId() {
		logger.info("Testing retrieve a singer by id :");
		Singer singer = restTemplate.getForObject(URL_GET_SINGER_BY_ID, Singer.class, 1);
		assertNotNull(singer);
		logger.info(singer.toString());
	}

	@Test
	public void testUpdate() {
		Singer singer = restTemplate.getForObject(URL_UPDATE_SINGER, Singer.class, 1);
		singer.setFirstName("John clayton");
		System.out.println("Testing update singer by id :");
		restTemplate.put(URL_UPDATE_SINGER, singer, 1);
		logger.info("Singer update successfully: " + singer);
	}


	private void listSingers(Singers singers) {
		singers.getSingers().forEach(s -> logger.info(s.toString()));
	}
}
