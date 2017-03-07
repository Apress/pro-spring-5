package com.apress.prospring5.ch3.config;

import com.apress.prospring5.ch3.annotated.Singer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

import java.util.Arrays;
import java.util.Map;

/**
 * Created by iuliana.cosmina on 2/19/17.
 */
public class AliasConfigDemo {

	@Configuration
	static class AliasBeanConfig {
		//@Bean(name="johnMayer")
		//@Bean(name = "jon johnny,jonathan;jim")
		@Bean(name = {"johnMayer", "john", "jonathan", "johnny"})
		public Singer singer() {
			return new Singer();
		}
	}

	public static void main(String... args) {
		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(AliasBeanConfig.class);
		Map<String, Singer> beans = ctx.getBeansOfType(Singer.class);
		beans.entrySet().stream().forEach(b ->
				System.out.println(
						"id: " + b.getKey() + "\n aliases: "
								+ Arrays.toString(ctx.getAliases(b.getKey())) + "\n")
		);
		ctx.close();
	}
}
