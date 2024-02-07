package com.attendance.system.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.service.CourseService;
import com.attendance.system.service.HomeService;


@Controller
public class HomeController {
	
	@Autowired
	private CourseService courses;
	
	@Autowired
	private HomeService homeService;

	@RequestMapping("/home")
	public ModelAndView home(ModelAndView mv) {
		mv.setViewName("index");
		mv.addObject("courses", courses.getAllCources().getBody());
		
		homeService.getAttendanceData();
		
		return mv;
	}

	@RequestMapping("/")
	public String index() {
		return "redirect:/home";
	}

}
