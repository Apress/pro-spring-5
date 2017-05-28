package com.apress.prospring5.ch9.services;

import com.apress.prospring5.ch9.entities.Singer;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.List;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

	@PersistenceContext(unitName = "emfA")
	private EntityManager emA;
	@PersistenceContext(unitName = "emfB")
	private EntityManager emB;

	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		throw new NotImplementedException("findAll");
	}

	@Override
	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		throw new NotImplementedException("findById");
	}

	@Override
	public Singer save(Singer singer) {
		Singer singerB = new Singer();
		singerB.setFirstName(singer.getFirstName());
		singerB.setLastName(singer.getLastName());
		if (singer.getId() == null) {
			emA.persist(singer);
			emB.persist(singerB);
			//throw new JpaSystemException(new PersistenceException());
		} else {
			emA.merge(singer);
			emB.merge(singer);
		}
		emA.flush();
		emB.flush();
		return singer;
	}

	@Override
	public long countAll() {
		return 0;
	}
}
