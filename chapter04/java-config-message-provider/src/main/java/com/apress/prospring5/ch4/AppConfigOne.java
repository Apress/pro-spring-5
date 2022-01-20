package com.apress.prospring5.ch4;

import com.apress.prospring5.ch2.decoupled.MessageProvider;
import com.apress.prospring5.ch2.decoupled.MessageRenderer;
import com.apress.prospring5.ch2.decoupled.StandardOutMessageRenderer;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource(value = "classpath:message.properties")
public class AppConfigOne {

	final Environment env;

	public AppConfigOne(Environment env) {
		this.env = env;
	}

	@Bean
	@Lazy
	public MessageProvider messageProvider() {
		return new ConfigurableMessageProvider(env.getProperty("message"));
	}

	@Bean(name = "messageRenderer")
	@Scope(value="prototype")
	@DependsOn(value="messageProvider")
	public MessageRenderer messageRenderer() {
		MessageRenderer renderer = new StandardOutMessageRenderer();
		renderer.setMessageProvider(messageProvider());
		return renderer;
	}
}
