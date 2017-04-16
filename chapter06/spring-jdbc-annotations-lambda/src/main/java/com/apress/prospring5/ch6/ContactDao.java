package com.apress.prospring5.ch6;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    String findFirstNameById(Long id);
    List<Contact> findAllWithAlbums();
    void insert(Contact contact);
    void insertWithAlbums(Contact contact);
    void update(Contact contact);
}
