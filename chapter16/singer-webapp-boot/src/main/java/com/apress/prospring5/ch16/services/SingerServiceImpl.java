package com.apress.prospring5.ch16.services;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.repos.SingerRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

	private SingerRepository singerRepository;

	@Override
	public List<Singer> findAll() {
		return Lists.newArrayList(singerRepository.findAll());
	}

	@Override
	public Singer findById(Long id) {
		return singerRepository.findById(id).get();
	}

	@Override
	public Singer save(Singer singer) {
		return singerRepository.save(singer);
	}

	@Autowired
	public void setSingerRepository(SingerRepository singerRepository) {
		this.singerRepository = singerRepository;
	}

}
