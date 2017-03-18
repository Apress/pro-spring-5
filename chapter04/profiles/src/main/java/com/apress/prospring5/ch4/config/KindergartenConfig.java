package com.apress.prospring5.ch4.config;

import com.apress.prospring5.ch4.FoodProviderService;
import com.apress.prospring5.ch4.kindergarten.FoodProviderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by iuliana.cosmina on 3/18/17.
 */
@Configuration
@Profile("kindergarten")
public class KindergartenConfig {

	@Bean
	public FoodProviderService foodProviderService(){
		return new FoodProviderServiceImpl();
	}
}