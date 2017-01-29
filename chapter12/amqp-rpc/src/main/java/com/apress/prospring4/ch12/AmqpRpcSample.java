package com.apress.prospring4.ch12;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AmqpRpcSample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/amqp-rpc-app-context.xml");
        ctx.refresh();

        WeatherService weatherService = ctx.getBean(WeatherService.class);
        System.out.println("Forecast for FL: " + weatherService.getForecast("FL"));
        System.out.println("Forecast for MA: " + weatherService.getForecast("MA"));
        System.out.println("Forecast for CA: " + weatherService.getForecast("CA"));

        ctx.close();
    }
}
