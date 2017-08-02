package com.apress.prospring5.ch18.services;

import java.util.List;

import com.apress.prospring5.ch18.entities.Singer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer save(Singer singer);
}
