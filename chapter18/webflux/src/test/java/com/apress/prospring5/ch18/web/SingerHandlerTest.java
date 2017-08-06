package com.apress.prospring5.ch18;

import com.apress.prospring5.ch18.entities.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

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
	public void getPerson() throws Exception {
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
}
