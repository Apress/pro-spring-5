package com.apress.prospring5.ch12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class WeatherServiceImpl {

	private static Logger logger = LoggerFactory.getLogger(WeatherServiceImpl.class);

	@RabbitListener(containerFactory="rabbitListenerContainerFactory", queues="forecasts")
	public void getForecast(String stateCode) {
		if ("FL".equals(stateCode)) {
			logger.info("Hot");
		} else if ("MA".equals(stateCode)) {
			logger.info("Cold");
		} else {
			logger.info("Not available at this time");
		}
	}
}
