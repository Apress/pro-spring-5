package com.apress.prospring5.ch8.service;

import com.apress.prospring5.ch8.entities.Singer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.persistence.PersistenceContext;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service("jpaSingerService")
@Repository
@Transactional
@SuppressWarnings("unchecked")
public class SingerServiceImpl implements SingerService {
    final static String ALL_SINGER_NATIVE_QUERY =
        "select id, first_name, last_name, birth_date, version from singer";

    private static Logger logger = LoggerFactory.getLogger(SingerServiceImpl.class);


    @PersistenceContext
    private EntityManager em;

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAll() {
        return em.createNamedQuery(Singer.FIND_ALL, Singer.class).getResultList();
    }
    
    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAllWithAlbum() {
        List<Singer> singers = em.createNamedQuery(Singer.FIND_ALL_WITH_ALBUM, Singer.class).getResultList();
        return singers;
    }

    @Transactional(readOnly=true)
    @Override
    public Singer findById(Long id) {
        TypedQuery<Singer> query = em.createNamedQuery(Singer.FIND_SINGER_BY_ID, Singer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Singer save(Singer singer) {
        if (singer.getId() == null) {
            logger.info("Inserting new singer");
            em.persist(singer);
        } else {
            em.merge(singer);
            logger.info("Updating existing singer");
        }

        logger.info("Singer saved with id: " + singer.getId());

        return singer;
    }

    @Override
    public void delete(Singer singer) {
        Singer mergedContact = em.merge(singer);
        em.remove(mergedContact);

        logger.info("Singer with id: " + singer.getId()  + " deleted successfully");
    }

    @Transactional(readOnly=true)
    @Override
    public List<Singer> findAllByNativeQuery() {
        return em.createNativeQuery(ALL_SINGER_NATIVE_QUERY, "singerResult").getResultList();
    }    
}
