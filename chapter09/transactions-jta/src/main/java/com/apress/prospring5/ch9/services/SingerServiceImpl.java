package com.apress.prospring5.ch9.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apress.prospring5.ch9.entities.Singer;

@Service("singerService")
@Repository
@Transactional
public class SingerServiceImpl implements SingerService {

    @PersistenceContext(unitName="emfA")
    private EntityManager emA;

    @PersistenceContext(unitName="emfB")
    private EntityManager emB;

    @Override
    @Transactional(readOnly=true)
    public List<Singer> findAll() {
        return null;
    }

    @Override
    @Transactional(readOnly=true)
    public Singer findById(Long id) {
        return null;
    }

    @Override
    public Singer save(Singer singer) {
        Singer singerB = new Singer();
        singerB.setFirstName(singer.getFirstName());
        singerB.setLastName(singer.getLastName());
        if (singer.getId() == null) {
            emA.persist(singer);
            //emB.persist(singerB);
            throw new JpaSystemException(new PersistenceException());
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
