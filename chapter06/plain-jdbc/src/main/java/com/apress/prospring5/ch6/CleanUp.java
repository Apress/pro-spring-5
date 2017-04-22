package com.apress.prospring5.ch6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by iuliana.cosmina on 4/15/17.
 */
public class CleanUp {
	private static Logger logger = LoggerFactory.getLogger(CleanUp.class);

	private JdbcTemplate jdbcTemplate;

	public CleanUp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private void destroy() {
		logger.info(" ... Deleting database files.");
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
	}
}
