package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.*;
import com.apress.prospring5.ch6.entities.Album;
import com.apress.prospring5.ch6.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("singerDao")
public class JdbcSingerDao implements SingerDao {

	private static final Log LOG = LogFactory.getLog(JdbcSingerDao.class);
	private DataSource dataSource;
	private SelectAllSingers selectAllSingers;
	private SelectSingerByFirstName selectSingerByFirstName;
	private UpdateSinger updateSinger;
	private InsertSinger insertSinger;
	private InsertSingerAlbum insertSingerAlbum;
	private StoredFunctionFirstNameById storedFunctionFirstNameById;

	@Override
	public List<Singer> findAll() {
		return selectAllSingers.execute();
	}

	@Override
	public List<Singer> findByFirstName(String firstName) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", firstName);
		return selectSingerByFirstName.executeByNamedParam(paramMap);
	}

	@Override
	public String findFirstNameById(Long id) {
		List<String> result = storedFunctionFirstNameById.execute(id);
		return result.get(0);
	}

	@Override
	public void insert(Singer singer) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		singer.setId(keyHolder.getKey().longValue());
		LOG.info("New singer inserted with id: " + singer.getId());
	}

	@Override
	public void insertWithDetail(Singer singer) {
		insertSingerAlbum = new InsertSingerAlbum(dataSource);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		insertSinger.updateByNamedParam(paramMap, keyHolder);
		singer.setId(keyHolder.getKey().longValue());
		LOG.info("New singer inserted with id: " + singer.getId());
		List<Album> singerTelDetails =
				singer.getAlbums();
		if (singerTelDetails != null) {
			for (Album singerTelDetail : singerTelDetails) {
				paramMap = new HashMap<>();
				paramMap.put("singer_id", singer.getId());
				paramMap.put("tel_type", singerTelDetail.getTitle());
				paramMap.put("tel_number", singerTelDetail.getReleaseDate());
				insertSingerAlbum.updateByNamedParam(paramMap);
			}
		}
		insertSingerAlbum.flush();
	}

	@Override
	public List<Singer> findAllWithDetail() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(getDataSource());
		String sql = "select c.id, c.first_name, c.last_name, c.birth_date" +
				", a.id as album_id, t.title, t.release_date from singer c " +
				"left join album a on c.id = a.singer_id";
		return jdbcTemplate.query(sql, new SingerWithDetailExtractor());
	}

	@Override
	public void update(Singer singer) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("first_name", singer.getFirstName());
		paramMap.put("last_name", singer.getLastName());
		paramMap.put("birth_date", singer.getBirthDate());
		paramMap.put("id", singer.getId());
		updateSinger.updateByNamedParam(paramMap);
		LOG.info("Existing singer updated with id: " + singer.getId());
	}

	@Resource(name = "dataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.selectAllSingers = new SelectAllSingers(dataSource);
		this.selectSingerByFirstName = new SelectSingerByFirstName(dataSource);
		this.updateSinger = new UpdateSinger(dataSource);
		this.insertSinger = new InsertSinger(dataSource);
		this.storedFunctionFirstNameById = new StoredFunctionFirstNameById(dataSource);
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	private static final class SingerWithDetailExtractor
			implements ResultSetExtractor<List<Singer>> {

		public List<Singer> extractData(ResultSet rs) throws
				SQLException, DataAccessException {
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
					singer.getAlbums().add(album);
				}
			}
			return new ArrayList<>(map.values());
		}
	}

	@Override public String findNameById(Long id) {
		throw new NotImplementedException("findNameById");
	}

	@Override public String findLastNameById(Long id) {
		throw new NotImplementedException("findLastNameById");
	}

	@Override public void delete(Long singerId) {
		throw new NotImplementedException("delete");
	}
}
