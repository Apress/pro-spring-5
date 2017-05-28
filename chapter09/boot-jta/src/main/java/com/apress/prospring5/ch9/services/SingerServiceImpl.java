package com.apress.prospring5.ch9.services;

import com.apress.prospring5.ch9.entities.Singer;
import com.apress.prospring5.ch9.ex.AsyncXAResourcesException;
import com.apress.prospring5.ch9.repos.SingerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service("singerService")
@Transactional
public class SingerServiceImpl implements SingerService {

	private SingerRepository singerRepository;
	private JmsTemplate jmsTemplate;

	public SingerServiceImpl(SingerRepository singerRepository, JmsTemplate jmsTemplate) {
		this.singerRepository = singerRepository;
		this.jmsTemplate = jmsTemplate;
	}

	@Override
	public Singer save(Singer singer) {
		jmsTemplate.convertAndSend("singers", "Just saved:" + singer);
		if(singer == null) {
			throw new AsyncXAResourcesException("Simulation of something going wrong.");
		}
		singerRepository.save(singer);
		return singer;
	}

	@Override public long count() {
		return singerRepository.count();
	}
}
