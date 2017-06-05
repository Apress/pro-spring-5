package com.apress.prospring5.ch10.config;

import com.apress.prospring5.ch10.Singer;
import com.apress.prospring5.ch10.SingerToAnotherSingerConverter;
import com.apress.prospring5.ch10.StringToDateTimeConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 5/31/17.
 */
@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch10")
public class AppConfig {

	@Bean
	public Singer john() throws Exception {
		Singer singer = new Singer();
		singer.setFirstName("John");
		singer.setLastName("Mayer");
		singer.setPersonalSite(new URL("http://johnmayer.com/"));
		singer.setBirthDate(converter().convert("1977-10-16"));
		return singer;
	}

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		Set<Converter> convs = new HashSet<>();
		convs.add(converter());
		convs.add(singerConverter());
		conversionServiceFactoryBean.setConverters(convs);
		conversionServiceFactoryBean.afterPropertiesSet();
		return conversionServiceFactoryBean;
	}

	@Bean
	StringToDateTimeConverter converter() {
		return new StringToDateTimeConverter();
	}

	@Bean
	SingerToAnotherSingerConverter singerConverter() {
		return new SingerToAnotherSingerConverter();
	}
}
