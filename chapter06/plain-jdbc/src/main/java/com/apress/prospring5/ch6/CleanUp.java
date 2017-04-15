package com.apress.prospring5.ch6;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created by iuliana.cosmina on 4/15/17.
 */
public class CleanUp {
	private JdbcTemplate jdbcTemplate;

	public CleanUp(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private void destroy() {
		jdbcTemplate.execute("DROP ALL OBJECTS DELETE FILES;");
	}
}
