package com.apress.prospring4.ch16;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ContactService {
    List<Contact> findAll();
    Contact findById(Long id);
    Contact save(Contact contact);
    Page<Contact> findAllByPage(Pageable pageable);
}
