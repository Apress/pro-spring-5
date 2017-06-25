package com.apress.prospring5.ch13;

import com.apress.prospring5.ch13.entities.Singer;

import java.util.List;

public interface SingerService {
    List<Singer> findAll();
    Singer findById(Long id);
    Singer findByFirstNameAndLastName(String firstName, String lastName);
    Singer save(Singer singer);
    void delete(Singer singer);
}
