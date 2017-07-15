package com.apress.prospring5.ch16.services;

import com.apress.prospring5.ch16.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by iuliana.cosmina on 4/23/17.
 */
@Service
public class DBInitializer {

	private Logger logger = LoggerFactory.getLogger(DBInitializer.class);
	@Autowired
	SingerService singerService;

	@PostConstruct
	public void initDB() {
		logger.info("Starting database initialization...");
		Singer singer = new Singer();
		singer.setFirstName("John");
		singer.setLastName("Mayer");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Eric");
		singer.setLastName("Clapton");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1945, 2, 30)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("John");
		singer.setLastName("Butler");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1975, 3, 1)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("B.B.");
		singer.setLastName("King");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1925, 9, 16)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Jimi");
		singer.setLastName("Hendrix");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1942, 11, 27)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Jimmy");
		singer.setLastName("Page");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1944, 1, 9)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Eddie");
		singer.setLastName("Van Halen");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1955, 1, 26)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Saul (Slash)");
		singer.setLastName("Hudson");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1965, 7, 23)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Stevie");
		singer.setLastName("Ray Vaughan");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1954, 10, 3)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("David");
		singer.setLastName("Gilmour");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1946, 3, 6)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Kirk");
		singer.setLastName("Hammett");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1992, 11, 18)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Angus");
		singer.setLastName("Young");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1955, 3, 31)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Dimebag");
		singer.setLastName("Darrell");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1966, 8, 20)).getTime().getTime()));
		singerService.save(singer);

		singer = new Singer();
		singer.setFirstName("Carlos");
		singer.setLastName("Santana");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1947, 7, 20)).getTime().getTime()));
		singerService.save(singer);

		logger.info("Database initialization finished.");
	}
}
