package com.apress.prospring5.ch9.services;

import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.ex.AsyncXAResourcesException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.util.ArrayList;
import java.util.List;

@Service("singerService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SingerServiceImpl implements SingerService {

	private static final String FIND_ALL= "select s from Singer s";

	@PersistenceContext(unitName = "emfA")
	private EntityManager emA;
	@PersistenceContext(unitName = "emfB")
	private EntityManager emB;

	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll()
	{
		List<Singer> singersFromA = findAllInA();
		List<Singer> singersFromB = findAllInB();
		if (singersFromA.size()!= singersFromB.size()){
			throw new AsyncXAResourcesException("XA resources obj not contain the same expected data.");
		}
		Singer sA = singersFromA.get(0);
		Singer sB = singersFromB.get(0);
		if (!sA.getFirstName().equals(sB.getFirstName()))  {
			throw new AsyncXAResourcesException("XA resources obj not contain the same expected data.");
		}
		List<Singer> singersFromBoth = new ArrayList<>();
		singersFromBoth.add(sA);
		singersFromBoth.add(sB);
		return singersFromBoth;
	}

	private List<Singer> findAllInA(){
		return emA.createQuery(FIND_ALL).getResultList();
	}

	private List<Singer> findAllInB(){
		return emB.createQuery(FIND_ALL).getResultList();
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
		singerB.setBirthDate(singer.getBirthDate());
		if (singer.getId() == null) {
			emA.persist(singer);
			if(true) {
				throw new JpaSystemException(new PersistenceException("Simulation of something going wrong."));
			}
			emB.persist(singerB);
		} else {
			emA.merge(singer);
			emB.merge(singer);
		}
		return singer;
	}

	@Override
	public long countAll() {
		return 0;
	}
}
