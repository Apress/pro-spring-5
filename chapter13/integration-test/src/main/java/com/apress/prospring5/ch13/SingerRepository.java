package com.apress.prospring5.ch13;

import com.apress.prospring5.ch13.entities.Singer;
import org.springframework.data.repository.CrudRepository;

public interface SingerRepository extends CrudRepository<Singer, Long> {
    Singer findByFirstNameAndLastName(String firstName, String lastName);
}
