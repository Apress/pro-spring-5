package com.apress.prospring5.ch18;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Created by iuliana.cosmina on 8/5/17.
 */
public class ReactiveSampleTest {
	private final Logger logger = LoggerFactory.getLogger(ReactiveSampleTest.class);

	@Test
	public void testFlux() {
		List<String> elements = new ArrayList<>();
		Flux.just("hope", "sadness", "smile", "tear", "grin", "cry", "laugh").log().subscribe(new Subscriber<String>() {
			private Subscription sub;
			int onNextAmount;

			@Override public void onSubscribe(Subscription s) {
				s.request(Long.MAX_VALUE);
				this.sub = s;
			}

			@Override public void onNext(String s) {
				String tmp = s.toUpperCase();
				elements.add(tmp);
				onNextAmount++;
				if (onNextAmount % 2 == 0) {
					sub.request(2);
				}
			}

			@Override public void onError(Throwable t) {
				logger.error("Unexpected issue!", t);
				fail("This Flux test failed!");
			}

			@Override public void onComplete() {
				logger.info("All done!");
				elements.forEach(logger :: info);
				assertTrue(elements.size() == 7);
			}
		});
	}
}
