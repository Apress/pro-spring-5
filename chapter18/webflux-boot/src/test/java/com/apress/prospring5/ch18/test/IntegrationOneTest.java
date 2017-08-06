package com.apress.prospring5.ch18.test;

import com.apress.prospring5.ch18.entities.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

import java.time.Duration;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IntegrationOneTest {
	private final Logger logger = LoggerFactory.getLogger(IntegrationOneTest.class);

	@Test
	public void test1One() {
		Flux<String> numbers = Flux.just("1", "2", "3");
		Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
		Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
	}

	@Test
	public void test2One() {
		Flux<String> numbers = Flux.just("11", "22", "33");
		Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
		Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
	}

}
