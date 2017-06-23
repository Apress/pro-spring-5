package com.apress.prospring5.ch12.controller;

import com.apress.prospring5.ch12.entities.Singer;
import com.apress.prospring5.ch12.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/singer")
public class SingerController {

	final Logger logger = LoggerFactory.getLogger(SingerController.class);
	@Autowired
	private SingerService singerService;

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/listdata")
	public List<Singer> listData() {
		return singerService.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/{id}")
	public Singer findSingerById(@PathVariable Long id) {
		return singerService.findById(id);
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value="/")
	public Singer create(@RequestBody Singer singer) {
		logger.info("Creating singer: " + singer);
		singerService.save(singer);
		logger.info("Singer created successfully with info: " + singer);
		return singer;
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value="/{id}")
	public void update(@RequestBody Singer singer,
			@PathVariable Long id) {
		logger.info("Updating singer: " + singer);
		singerService.save(singer);
		logger.info("Singer updated successfully with info: " + singer);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping(value="/{id}")
	public void delete(@PathVariable Long id) {
		logger.info("Deleting singer with id: " + id);
		Singer singer = singerService.findById(id);
		singerService.delete(singer);
		logger.info("Singer deleted successfully");
	}
}
