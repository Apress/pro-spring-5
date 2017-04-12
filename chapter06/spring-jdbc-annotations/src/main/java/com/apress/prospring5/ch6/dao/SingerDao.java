package com.apress.prospring5.ch6.dao;

import com.apress.prospring5.ch6.entities.Singer;

import java.util.List;

public interface SingerDao {
    List<Singer> findAll();
    List<Singer> findByFirstName(String firstName);
    String findFirstNameById(Long id);
    List<Singer> findAllWithDetail();
    void insert(Singer contact);
    void insertWithDetail(Singer contact);
    void update(Singer contact);
}
