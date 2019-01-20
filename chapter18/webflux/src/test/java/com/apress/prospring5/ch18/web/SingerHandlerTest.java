package com.apress.prospring5.ch18.web;

import com.apress.prospring5.ch18.ServerConfig;
import com.apress.prospring5.ch18.config.TestConfig;
import com.apress.prospring5.ch18.entities.Singer;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ServerConfig.class, TestConfig.class})
public class SingerHandlerTest {

	@Autowired
	WebTestClient testClient;

	@Test
	public void getSinger() throws Exception {
		List<Singer> result = this.testClient.get()
				.uri("/1")
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Singer.class).hasSize(1).returnResult().getResponseBody();
		Singer singer = result.get(0);
		assertAll("singer", () ->
		{
			assertNotNull(singer);
			assertAll("singer",
					() -> assertEquals("John", singer.getFirstName()),
					() -> assertEquals("Mayer", singer.getLastName()));
		});
	}

	@Test
	public void getSingerNotFound() throws Exception {
		this.testClient.get()
				.uri("/99")
				.exchange()
				.expectStatus().isNotFound();
	}

	@Test
	public void getAll() throws Exception {
		this.testClient.get().uri("/").accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk()
				.expectHeader().contentType(MediaType.APPLICATION_JSON)
				.expectBodyList(Singer.class).hasSize(14).consumeWith(Assertions::assertNotNull);
	}

	@Ignore
	@Test
	public void create() throws Exception {
		Singer singer = new Singer();
		singer.setFirstName("Ed");
		singer.setLastName("Sheeran");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1991, 2, 17)).getTime().getTime()));

		this.testClient.post().uri("/").body(Mono.just(singer) , Singer.class).exchange().expectStatus().isOk();
	}
}


