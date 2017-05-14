package com.apress.prospring5.ch8;

import com.apress.prospring5.ch8.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 5/12/17.
 */
@SpringBootApplication(scanBasePackages = "com.apress.prospring5.ch8.config")
public class Application implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(Application.class);
	@Autowired
	SingerRepository singerRepository;

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.in.read();
		ctx.close();
	}

	@Transactional(readOnly = true)
	@Override public void run(String... args) throws Exception {
		List<Singer> singers = singerRepository.findByFirstName("John");
		listSingersWithAlbum(singers);
	}

	private static void listSingersWithAlbum(List<Singer> singers) {
		logger.info(" ---- Listing singers with instruments:");
		singers.forEach(singer -> {
			logger.info(singer.toString());
			if (singer.getAlbums() != null) {
				singer.getAlbums().forEach(album -> logger.info("\t" + album.toString()));
			}
			if (singer.getInstruments() != null) {
				singer.getInstruments().forEach(instrument -> logger.info("\t" + instrument.getInstrumentId()));
			}
		});
	}
}
