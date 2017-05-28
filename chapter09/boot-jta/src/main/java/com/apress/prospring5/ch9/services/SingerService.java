package com.apress.prospring5.ch9.services;

import com.apress.prospring5.ch9.entities.Singer;

public interface SingerService {
    Singer save(Singer singer);

    long count();

}
