package com.apress.prospring5.ch6.config;

import com.apress.prospring5.ch6.CleanUp;
import com.apress.prospring5.ch6.JdbcSingerDao;
import com.apress.prospring5.ch6.dao.SingerDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 4/15/17.
 */
@Configuration
public class EmbeddedJdbcConfig {

	private static Logger logger = LoggerFactory.getLogger(EmbeddedJdbcConfig.class);

	@Bean
	public DataSource dataSource() {
		try {
			EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
			return dbBuilder.setType(EmbeddedDatabaseType.H2)
					.addScripts("classpath:db/h2/schema.sql", "classpath:db/h2/test-data.sql").build();
		} catch (Exception e) {
			logger.error("Embedded DataSource bean cannot be created!", e);
			return null;
		}
	}

	@Bean public JdbcTemplate jdbcTemplate(){
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource());
		return jdbcTemplate;
	}

	@Bean
	public SingerDao singerDao() {
		JdbcSingerDao dao = new JdbcSingerDao();
		dao.setJdbcTemplate(jdbcTemplate());
		return dao;
	}

	@Bean(destroyMethod = "destroy")
	public CleanUp cleanUp() {
		return new CleanUp(jdbcTemplate());
	}
}
