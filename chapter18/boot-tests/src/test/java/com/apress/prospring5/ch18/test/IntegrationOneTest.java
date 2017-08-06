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
public class IntegrationOneTest {

	private final Logger logger = LoggerFactory.getLogger(IntegrationOneTest.class);
	@Autowired FluxGenerator fluxGenerator;

	@Test
	public void test1One() {
		fluxGenerator.generate("1", "2", "3").collectList().block().forEach(s ->
			executeSlow(2000, s)
		);
	}

	@Test
	public void test2One() {
		fluxGenerator.generate("11", "22", "33").collectList().block().forEach(s -> executeSlow(1000, s));
	}

	private void executeSlow(int duration, String s) {
		try {
			Thread.sleep(duration);
			logger.info(s);
		} catch (InterruptedException e) {
		}
	}
}

