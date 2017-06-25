package com.apress.prospring5.ch13;

import com.apress.prospring5.ch13.entities.Singer;

import java.util.List;

public interface singerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
    void delete(Singer singer);
}
