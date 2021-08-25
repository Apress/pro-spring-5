package com.apress.prospring5.ch4;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"com.apress.prospring5.ch4.annotated"})
public class AppConfigTwo {

	final MessageProvider provider;

	public AppConfigTwo(MessageProvider provider) {
		this.provider = provider;
	}


	@Bean(name = "messageRenderer")
	public MessageRenderer messageRenderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(provider);
		return renderer;
	}
}
