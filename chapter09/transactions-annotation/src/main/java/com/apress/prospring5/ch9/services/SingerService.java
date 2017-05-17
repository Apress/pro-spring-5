package com.apress.prospring5.ch9.services;

import java.util.List;
import com.apress.prospring5.ch9.entities.Singer;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer contact);
    long countAll();
}

