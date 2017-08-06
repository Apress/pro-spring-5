package com.apress.prospring5.ch18.test.config;

import com.apress.prospring5.ch18.FluxGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 8/6/17.
 */
@Configuration
public class TestConfig {

	@Bean FluxGenerator generator(){
		return new FluxGenerator();
	}

}
