package com.apress.prospring5.ch16.web;

import com.apress.prospring5.ch16.entities.Singer;
import com.apress.prospring5.ch16.services.SingerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by iuliana.cosmina on 7/16/17.
 */
@Controller
@RequestMapping(value = "/singers")
public class SingerController {

	private final Logger logger = LoggerFactory.getLogger(SingerController.class);
	@Autowired SingerService singerService;

	@RequestMapping(method = RequestMethod.GET)
	public String list(Model uiModel) {
		logger.info("Listing singers");
		List<Singer> singers = singerService.findAll();
		uiModel.addAttribute("singers", singers);
		logger.info("No. of singers: " + singers.size());
		return "singers";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model uiModel) {
		Singer singer = singerService.findById(id);
		uiModel.addAttribute("singer", singer);
		return "show";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String updateForm(@PathVariable Long id, Model model) {
		model.addAttribute("singer", singerService.findById(id));
		return "update";
	}

	@RequestMapping(value = "/new", method = RequestMethod.GET)
	public String createForm(Model uiModel) {
		Singer singer = new Singer();
		uiModel.addAttribute("singer", singer);
		return "update";
	}

	@RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST)
	public String saveProduct(Singer singer) {
		singerService.save(singer);
		return "redirect:/singers/" + singer.getId();
	}
}
