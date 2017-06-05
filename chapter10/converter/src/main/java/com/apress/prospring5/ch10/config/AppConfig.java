package com.apress.prospring5.ch10.config;

import com.apress.prospring5.ch10.Singer;
import com.apress.prospring5.ch10.StringToDateTimeConverter;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.convert.converter.Converter;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by iuliana.cosmina on 5/31/17.
 */
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan(basePackages = "com.apress.prospring5.ch10")
public class AppConfig {

	@Value("${date.format.pattern}")
	private String dateFormatPattern;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public Singer john(@Value("${countrySinger.firstName}") String firstName,
			@Value("${countrySinger.lastName}") String lastName,
			@Value("${countrySinger.personalSite}") URL personalSite,
			@Value("${countrySinger.birthDate}") DateTime birthDate) throws Exception {
		Singer singer = new Singer();
		singer.setFirstName(firstName);
		singer.setLastName(lastName);
		singer.setPersonalSite(personalSite);
		singer.setBirthDate(birthDate);
		return singer;
	}

	@Bean
	public ConversionServiceFactoryBean conversionService() {
		ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
		Set<Converter> convs = new HashSet<>();
		convs.add(converter());
		conversionServiceFactoryBean.setConverters(convs);
		conversionServiceFactoryBean.afterPropertiesSet();
		return conversionServiceFactoryBean;
	}

	@Bean
	StringToDateTimeConverter converter(){
		StringToDateTimeConverter conv = new StringToDateTimeConverter();
		conv.setDatePattern(dateFormatPattern);
		conv.init();
		return conv;
	}
}
