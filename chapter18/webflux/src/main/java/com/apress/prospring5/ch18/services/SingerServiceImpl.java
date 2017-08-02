package com.apress.prospring5.ch18.services;

import com.apress.prospring5.ch18.entities.Singer;
import com.apress.prospring5.ch18.repos.ReactiveSingerRepo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service("singerService")
public class SingerServiceImpl implements SingerService {

	private ReactiveSingerRepo reactiveSingerRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Singer> findAll() {
		return Lists.newArrayList(reactiveSingerRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public Singer findById(Long id) {
		return reactiveSingerRepository.findById(id).get();
	}

	@Override
	public Singer save(Singer singer) {
		return reactiveSingerRepository.save(singer);
	}

	@Autowired
	public void setReactiveSingerRepository(ReactiveSingerRepo reactiveSingerRepository) {
		this.reactiveSingerRepository = reactiveSingerRepository;
	}

}
