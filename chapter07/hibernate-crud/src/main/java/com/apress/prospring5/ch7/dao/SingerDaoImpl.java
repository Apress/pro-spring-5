package com.apress.prospring5.ch7.dao;

import com.apress.prospring5.ch7.entities.Singer;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by iuliana.cosmina on 4/21/17.
 */
@SuppressWarnings("unchecked")
@Transactional
@Repository("singerDao")
public class SingerDaoImpl implements SingerDao {

	private static final Log logger = LogFactory.getLog(SingerDaoImpl.class);
	private SessionFactory sessionFactory;

	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Singer s").list();
	}

	@Transactional(readOnly = true)
	public List<Singer> findAllWithAlbum() {
		return sessionFactory.getCurrentSession().
				getNamedQuery("Singer.findAllWithAlbum").list();
	}

	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return (Singer) sessionFactory.getCurrentSession().
				getNamedQuery("Singer.findById").
				setParameter("id", id).uniqueResult();
	}

	public Singer save(Singer singer) {
		sessionFactory.getCurrentSession().saveOrUpdate(singer);
		logger.info("Singer saved with id: " + singer.getId());
		return singer;
	}

	public void delete(Singer singer) {
		sessionFactory.getCurrentSession().delete(singer);
		logger.info("Singer deleted with id: " + singer.getId());
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
