package com.apress.prospring4.ch6;

import java.util.List;

public interface ContactDao {
    List<Contact> findAll();
    String findLastNameById(Long id);
}
