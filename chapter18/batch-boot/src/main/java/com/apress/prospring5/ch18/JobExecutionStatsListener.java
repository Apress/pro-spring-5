package com.apress.prospring5.ch18;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class JobExecutionStatsListener extends JobExecutionListenerSupport {

	public static Logger logger = LoggerFactory.getLogger(JobExecutionStatsListener.class);

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public JobExecutionStatsListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info(" --> Singers were saved to the database. Printing results ...");
			jdbcTemplate.query("SELECT first_name, last_name, song FROM SINGER",
					(rs, row) -> new Singer(rs.getString(1),
							rs.getString(2), rs.getString(3))).forEach(
					singer -> logger.info(singer.toString())
			);
		}
	}
}
