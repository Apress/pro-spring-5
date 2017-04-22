package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Instrument;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by iuliana.cosmina on 4/23/17.
 */
@Transactional
@Repository("instrumentDao")
public class InstrumentDaoImpl implements InstrumentDao {

	private static final Log logger = LogFactory.getLog(SingerDaoImpl.class);
	private SessionFactory sessionFactory;


	@Override public Instrument save(Instrument instrument) {
		sessionFactory.getCurrentSession().saveOrUpdate(instrument);
		logger.info("Instrument saved with id: " + instrument.getInstrumentId());
		return instrument;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
