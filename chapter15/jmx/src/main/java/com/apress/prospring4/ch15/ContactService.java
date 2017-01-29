package com.apress.prospring4.ch15;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
