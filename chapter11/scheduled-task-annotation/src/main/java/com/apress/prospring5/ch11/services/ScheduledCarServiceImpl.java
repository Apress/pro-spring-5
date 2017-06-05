package com.apress.prospring5.ch11.services;

import com.apress.prospring5.ch11.ents.Car;
import com.apress.prospring5.ch11.repos.CarRepository;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by iuliana.cosmina on 6/5/17.
 */
@Service("scheduledCarService")
@Repository
@Transactional
public class ScheduledCarServiceImpl extends CarServiceImpl{
	final Logger logger = LoggerFactory.getLogger(ScheduledCarServiceImpl.class);

	@Override
	@Scheduled(fixedDelay=10000)
	public void updateCarAgeJob() {
		List<Car> cars = findAll();

		DateTime currentDate = DateTime.now();
		logger.info("Car age update job started");

		for (Car car: cars) {
			int age = Years.yearsBetween(car.getManufactureDate(), currentDate).getYears();

			car.setAge(age);
			save(car);
			logger.info("Car age update --> " + car);
		}

		logger.info("Car age update job completed successfully");
	}
}
