package com.apress.prospring5.ch4.config;

import com.apress.prospring5.ch4.DestructiveBeanWithJSR250;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by iuliana.cosmina on 2/27/17.
 */
public class DestructiveBeanConfigDemo {

	@Configuration
	static class DestructiveBeanConfig {

		@Lazy
		@Bean(initMethod = "afterPropertiesSet", destroyMethod = "destroy")
		DestructiveBeanWithJSR250 destructiveBean() {
			DestructiveBeanWithJSR250 destructiveBean = new DestructiveBeanWithJSR250();
			destructiveBean.setFilePath(System.getProperty("java.io.tmpdir") +
					System.getProperty("file.separator") + "test.txt");
			return destructiveBean;
		}

	}

	public static void main(String... args) {
		GenericApplicationContext ctx =
				   new AnnotationConfigApplicationContext(DestructiveBeanConfig.class);

		ctx.getBean(DestructiveBeanWithJSR250.class);
		ctx.registerShutdownHook();
	}

}
