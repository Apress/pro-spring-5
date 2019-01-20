package com.apress.prospring5.ch18.test;

import com.apress.prospring5.ch18.FluxGenerator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationTwoTest {
	private final Logger logger = LoggerFactory.getLogger(IntegrationTwoTest.class);

	@Autowired FluxGenerator fluxGenerator;

	@Test
	public void test1Two() {
		fluxGenerator.generate("a", "b", "c").collectList().block().forEach(logger::info);
	}

	@Test
	public void test2Two() {
		fluxGenerator.generate( "aa", "bb", "cc").collectList().block().forEach(logger::info);
	}
}
