package com.apress.prospring5.ch12;

import com.apress.prospring5.ch12.config.RmiClientConfig;
import com.apress.prospring5.ch12.entities.Singer;
import com.apress.prospring5.ch12.services.SingerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@ContextConfiguration(classes = RmiClientConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RmiTests {

	@Autowired
	private SingerService singerService;

	@Test
	public void testRmiAll() {
		List<Singer> singers = singerService.findAll();
		assertEquals(3, singers.size());
	}

	@Test
	public void testRmiJohn() {
		List<Singer> singers = singerService.findByFirstName("John");
		assertEquals(2, singers.size());
	}

}
