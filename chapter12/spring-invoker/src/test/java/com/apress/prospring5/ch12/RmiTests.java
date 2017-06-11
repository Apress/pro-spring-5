package com.apress.prospring5.ch12;

import com.apress.prospring5.ch12.config.RmiClientConfig;
import com.apress.prospring5.ch12.entities.Singer;
import com.apress.prospring5.ch12.services.DBInitializer;
import com.apress.prospring5.ch12.services.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringRunner.class)
public class RmiTests {
	private Logger logger = LoggerFactory.getLogger(RmiTests.class);

	@Autowired
	private SingerService singerService;

	@Test
	public void testRmiAll() {
		List<Singer> singers = singerService.findAll();
		assertEquals(3, singers.size());
		listSingers(singers);
	}

	@Test
	public void testRmiJohn() {
		List<Singer> singers = singerService.findByFirstName("John");
		assertEquals(2, singers.size());
		listSingers(singers);
	}

	private void listSingers(List<Singer> singers){
		singers.forEach(s -> logger.info(s.toString()));
	}
}
