package com.apress.prospring5.ch18.test;

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
public class IntegrationTwoTest {
	private final Logger logger = LoggerFactory.getLogger(IntegrationTwoTest.class);


	@Test
	public void test1Two() {
		Flux<String> numbers = Flux.just("a", "b", "c");
		Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
		Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
	}

	@Test
	public void test2Two() {
		Flux<String> numbers = Flux.just("aa", "bb", "bb");
		Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(3));
		Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(logger::info);
	}
}
