package com.apress.prospring5.ch6;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    String findLastNameById(Long id);
}
