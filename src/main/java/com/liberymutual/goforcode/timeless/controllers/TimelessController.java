package com.liberymutual.goforcode.timeless.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.liberymutual.goforcode.timeless.models.Timesheet;
import com.liberymutual.goforcode.timeless.services.TimelessRepository;

@Controller
@RequestMapping("/")
public class TimelessController {

	private TimelessRepository repository;

	public TimelessController(TimelessRepository repository) {
		this.repository = repository;
	}

	@GetMapping("")
	public String redirectToApplication() {
		return "redirect:/timeless";
	}

	@GetMapping("timeless")
	public ModelAndView timesheet() {
		ModelAndView mv = new ModelAndView("time/default");
		List<Timesheet> items = repository.getAll();
		mv.addObject("timesheetList", items); // changed this, check html
		mv.addObject("hasTimesheetList", !items.isEmpty()); // check html
		return mv;
	}

	@GetMapping("update")
	public String showDefault(Model model) {

		List<Timesheet> items = repository.getAll();

		return "time/default";
	}

	@PostMapping("timeless")
	public String create(Timesheet item, String buttons) {
		
		if(buttons.equals("submit")) {
			repository.create(item);
		}
		else {
			
		}

		return "redirect:/timeless";
	}

}
