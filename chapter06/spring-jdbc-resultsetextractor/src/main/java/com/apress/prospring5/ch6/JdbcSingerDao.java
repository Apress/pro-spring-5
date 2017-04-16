package com.apress.prospring5.ch6;

import com.apress.prospring5.ch6.dao.SingerDao;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcSingerDao implements SingerDao, InitializingBean {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	@Override
	public List<Singer> findAllWithAlbums() {
		String sql = "select s.id, s.first_name, s.last_name, s.birth_date" +
				", a.id as album_id, a.title, a.release_date from singer s " +
				"left join album a on s.id = a.singer_id";
		return namedParameterJdbcTemplate.query(sql, rs -> {
			Map<Long, Singer> map = new HashMap<>();
			Singer singer;
			while (rs.next()) {
				Long id = rs.getLong("id");
				singer = map.get(id);
				if (singer == null) {
					singer = new Singer();
					singer.setId(id);
					singer.setFirstName(rs.getString("first_name"));
					singer.setLastName(rs.getString("last_name"));
					singer.setBirthDate(rs.getDate("birth_date"));
					singer.setAlbums(new ArrayList<>());
					map.put(id, singer);
				}
				Long albumId = rs.getLong("album_id");
				if (albumId > 0) {
					Album album = new Album();
					album.setId(albumId);
					album.setSingerId(id);
					album.setTitle(rs.getString("title"));
					album.setReleaseDate(rs.getDate("release_date"));
					singer.addAbum(album);
				}
			}
			return new ArrayList<>(map.values());
		});
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (namedParameterJdbcTemplate == null) {
			throw new BeanCreationException("Null NamedParameterJdbcTemplate on SingerDao");
		}
	}

	@Override
	public String findLastNameById(Long id) {
		String sql = "SELECT last_name FROM singer WHERE id = :singerId";
		Map<String, Object> namedParameters = new HashMap<>();
		namedParameters.put("singerId", id);
		return namedParameterJdbcTemplate.queryForObject(sql,
				namedParameters, String.class);
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		throw new NotImplementedException("findByFirstName");
	}

	@Override
	public String findFirstNameById(Long id) {
		throw new NotImplementedException("findFirstNameById");
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

	@Override public void insertWithAlbum(Singer singer) {
		throw new NotImplementedException("insertWithAlbum");
	}

	@Override public List<Singer> findAll() {
		throw new NotImplementedException("findAll");
	}

	@Override public String findNameById(Long id) {
		throw new NotImplementedException("findNameById");
	}
}
