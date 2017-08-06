package com.apress.prospring5.ch18;

import com.apress.prospring5.ch18.web.SingerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.http.server.reactive.ServletHttpHandlerAdapter;

import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.HashMap;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * Created by iuliana.cosmina on 7/16/17.
 */
// Uncomment the annotations here and comment all in the ReactiveApplication class, before running this class,
// to use the SingerHandler class
@SpringBootApplication
public class SingerApplication {

	private static Logger logger = LoggerFactory.getLogger(SingerApplication.class);

	@Autowired
	SingerHandler singerHandler;

	public RouterFunction<ServerResponse> routingFunction() {
		return route(GET("/test"), serverRequest -> ok().body(fromObject("works!")))
				.andRoute(GET("/singers"), singerHandler.list)
				.andRoute(GET("/singers/{id}"), singerHandler::show)
				.andRoute(POST("/singers"), singerHandler.save)
				.filter((request, next) -> {
					logger.info("Before handler invocation: " + request.path());
					return next.handle(request);
				});
	}

	@Bean
	public ServletRegistrationBean servletRegistrationBean() throws Exception {
		HttpHandler httpHandler = RouterFunctions.toHttpHandler(routingFunction());
		ServletRegistrationBean registrationBean = new ServletRegistrationBean<>(new ServletHttpHandlerAdapter(httpHandler), "/");
		registrationBean.setLoadOnStartup(1);
		registrationBean.setAsyncSupported(true);
		return registrationBean;
	}

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = new SpringApplicationBuilder(SingerApplication.class)
				.properties(
						new HashMap<String, Object>() {{
							put("server.port", "8080");
							put("spring.jpa.hibernate.ddl-auto", "create-drop");
						}}
				).run(args);
		assert (ctx != null);
		logger.info("Application started...");

		System.in.read();
		ctx.close();
	}
}
