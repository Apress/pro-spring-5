package com.apress.prospring5.ch18;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
@Component
public class FluxGenerator {

	public Flux<String> generate(String... args){
		return  Flux.just(args);
	}

}
