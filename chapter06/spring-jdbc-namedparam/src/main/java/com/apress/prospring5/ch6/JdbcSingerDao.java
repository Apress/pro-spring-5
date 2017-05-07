package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Override
	public String findNameById(Long id) {
		String sql = "SELECT first_name ||' '|| last_name FROM singer WHERE id = :singerId";
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);
		return namedParameterJdbcTemplate.queryForObject(sql,
				namedParameters, String.class);
	}

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
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

	@Override public List<Singer> findAllWithAlbums() {
		throw new NotImplementedException("findAllWithAlbums");
	}

	@Override public void insertWithAlbum(Singer singer) {
		throw new NotImplementedException("insertWithAlbum");
	}

	@Override public List<Singer> findAll() {
		throw new NotImplementedException("findAll");
	}

	@Override public List<Singer> findByFirstName(String firstName) {
		throw new NotImplementedException("findByFirstName");
	}

	@Override public String findLastNameById(Long id) {
		throw new NotImplementedException("findLastNameById");
	}

	@Override public String findFirstNameById(Long id) {
		throw new NotImplementedException("findFirstNameById");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (namedParameterJdbcTemplate == null) {
			throw new BeanCreationException("Null NamedParameterJdbcTemplate on SingerDao");
		}
	}
}
