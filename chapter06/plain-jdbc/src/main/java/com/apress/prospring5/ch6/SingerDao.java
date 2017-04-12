package com.apress.prospring5.ch6;

import java.util.List;

public interface SingerDao {

	List<Singer> findAll();

	List<Singer> findByFirstName(String firstName);

	String findLastNameById(Long id);

	String findFirstNameById(Long id);

	void insert(Singer singer);

	void update(Singer singer);

	void delete(Long singerId);
}

