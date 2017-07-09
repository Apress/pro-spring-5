package com.apress.prospring5.ch15;

import com.apress.prospring5.ch15.entities.Singer;
import com.apress.prospring5.ch15.services.SingerService;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.h2.jdbcx.JdbcDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;
import static org.hibernate.cfg.AvailableSettings.STATEMENT_FETCH_SIZE;

/**
 * Created by iuliana.cosmina on 5/12/17.
 */
@SpringBootApplication(scanBasePackages = "com.apress.prospring5.ch15.services")
public class Application implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String... args) throws Exception {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.in.read();
		ctx.close();
	}

	@Autowired SingerService singerService;

	@Override public void run(String... args) throws Exception {
		
	}
}
