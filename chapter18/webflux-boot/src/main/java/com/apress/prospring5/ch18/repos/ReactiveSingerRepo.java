package com.apress.prospring5.ch18.repos;

import com.apress.prospring5.ch18.entities.Singer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveSingerRepo {

	Mono<Singer> findById(Long id);

	Flux<Singer> findAll();

	Mono<Void> save(Mono<Singer> singer);
}
