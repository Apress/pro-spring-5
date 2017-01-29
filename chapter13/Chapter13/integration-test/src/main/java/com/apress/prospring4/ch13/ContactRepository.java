package com.apress.prospring4.ch13;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {
    Contact findByFirstNameAndLastName(String firstName, String lastName);
}
