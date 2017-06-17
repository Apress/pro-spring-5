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
	@RequestMapping(value = "/listdata", method = RequestMethod.GET)
	public List<Singer> listData() {
		return singerService.findAll();
	}

	@ResponseStatus(HttpStatus.OK)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Singer findSingerById(@PathVariable Long id) {
		return singerService.findById(id);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Singer create(@RequestBody Singer singer) {
		logger.info("Creating singer: " + singer);
		singerService.save(singer);
		logger.info("Singer created successfully with info: " + singer);
		return singer;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void update(@RequestBody Singer singer,
			@PathVariable Long id) {
		logger.info("Updating singer: " + singer);
		singerService.save(singer);
		logger.info("Singer updated successfully with info: " + singer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		logger.info("Deleting singer with id: " + id);
		Singer singer = singerService.findById(id);
		singerService.delete(singer);
		logger.info("Singer deleted successfully");
	}
}
