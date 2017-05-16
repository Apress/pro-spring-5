package com.apress.prospring5.ch8.services;

import com.apress.prospring5.ch8.entities.SingerAudit;
import com.apress.prospring5.ch8.repos.SingerAuditRepository;
import com.google.common.collect.Lists;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("singerAuditService")
@Transactional
public class SingerAuditServiceImpl implements SingerAuditService {

	@Autowired
	private SingerAuditRepository singerAuditRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(readOnly = true)
	public List<SingerAudit> findAll() {
		return Lists.newArrayList(singerAuditRepository.findAll());
	}

	/**
	 * API  changed in  2.0.0.M3 findOne became findById
	 * @param id
	 * @return
	 */
	public SingerAudit findById(Long id) {
		return singerAuditRepository.findById(id).get();
	}

	public SingerAudit save(SingerAudit singer) {
		return singerAuditRepository.save(singer);
	}

	@Transactional(readOnly = true)
	@Override
	public SingerAudit findAuditByRevision(Long id, int revision) {
		AuditReader auditReader = AuditReaderFactory.get(entityManager);
		return auditReader.find(SingerAudit.class, id, revision);
	}
}
