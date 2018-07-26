package com.sound.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/batteries")
public class BatteriesController {
	
	@GetMapping(path = "/index")
	public ModelAndView accountIndex() {
		ModelAndView mav = new ModelAndView("batteries/index");
		return mav;
	}
	
}
