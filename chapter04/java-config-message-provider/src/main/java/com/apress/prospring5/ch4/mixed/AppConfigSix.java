package com.apress.prospring5.ch4.mixed;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch4.annotated.ConfigurableMessageProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iuliana.cosmina on 3/15/17.
 */
@Configuration
public class AppConfigSix {

	@Bean
	public MessageProvider provider() {
		return new ConfigurableMessageProvider("Love on the weekend");
	}
}
