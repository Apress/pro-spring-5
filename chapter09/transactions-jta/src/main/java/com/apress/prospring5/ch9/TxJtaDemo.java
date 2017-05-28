package com.apress.prospring5.ch9;

import com.apress.prospring5.ch9.config.ServicesConfig;
import com.apress.prospring5.ch9.config.XAJpaConfig;
import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class TxJtaDemo {
	private static Logger logger = LoggerFactory.getLogger(TxJtaDemo.class);

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ServicesConfig.class,
				XAJpaConfig.class);
		SingerService singerService = ctx.getBean(SingerService.class);
		Singer singer = new Singer();
		singer.setFirstName("John");
		singer.setLastName("Mayer");
		singer.setBirthDate(new Date(
				(new GregorianCalendar(1977, 9, 16)).getTime().getTime()));
		singerService.save(singer);
		if (singer.getId() != null) {
			logger.info("--> Singer saved successfully");
		}  else {
			logger.error("--> Singer was not saved, check the configuration!!");
		}

		// check saving in both databases
		List<Singer> singers = singerService.findAll();
		if (singers.size()!= 2) {
			logger.error("--> Something went wrong.");
		} else {
			logger.info("--> Singers form both DBs: " + singers);
		}
		ctx.close();
	}
} 
