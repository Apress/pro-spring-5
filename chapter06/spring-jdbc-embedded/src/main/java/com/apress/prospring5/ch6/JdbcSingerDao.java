package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class JdbcSingerDao implements SingerDao, InitializingBean {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		jdbcTemplate.setDataSource(dataSource);
		MySQLErrorCodesTranslator errorTranslator =
				new MySQLErrorCodesTranslator();
		errorTranslator.setDataSource(dataSource);
		jdbcTemplate.setExceptionTranslator(errorTranslator);
		this.jdbcTemplate = jdbcTemplate;
	}

	public void afterPropertiesSet() throws Exception {
		if (dataSource == null) {
			throw new BeanCreationException("Must set dataSource on SingerDao");
		}
		if (jdbcTemplate == null) {
			throw new BeanCreationException("Null JdbcTemplate on SingerDao");
		}
	}

	@Override
	public String findFirstNameById(Long id) {
		return jdbcTemplate.queryForObject(
				"select first_name from singer where id = ?",
				new Object[]{id}, String.class);
	}


	@Override public List<Singer> findAll() {
		throw new NotImplementedException("findAll");
	}

	@Override public List<Singer> findByFirstName(String firstName) {
		throw new NotImplementedException("findByFirstName");
	}

	@Override public String findNameById(Long id) {
		return jdbcTemplate.queryForObject(
				"select first_name || ' ' || last_name from singer where id = ?",
				new Object[]{id}, String.class);
	}

	@Override public String findLastNameById(Long id) {
		throw new NotImplementedException("findLastNameById");
	}

	@Override public void insert(Singer singer) {
		throw new NotImplementedException("insert");
	}

	@Override public void update(Singer singer) {
		throw new NotImplementedException("update");
	}

	@Override public void delete(Long singerId) {
		throw new NotImplementedException("delete");
	}

	@Override public List<Singer> findAllWithDetail() {
		throw new NotImplementedException("findAllWithDetail");
	}

	@Override public void insertWithDetail(Singer singer) {
		throw new NotImplementedException("insertWithDetail");
	}
}
