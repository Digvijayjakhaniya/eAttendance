package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.model.Faculty;
import com.attendance.system.service.impl.FacultyServiceImpl;

@RestController
@RequestMapping("faculty")
public class FacultyController {


	@Autowired
	private FacultyServiceImpl facultyService;

	@RequestMapping
	public ModelAndView faculty() {
		return new ModelAndView("faculty").addObject("facultyList", facultyService.getAllFaculty().getBody());
	}

	@PostMapping("add")
	public ResponseEntity<String> addFaculty(Faculty faculty) {
		return facultyService.addFaculty(faculty);
	}
	
	@GetMapping("get")
	public ResponseEntity<List<Faculty>> getAllFaculty(){
		return facultyService.getAllFaculty();
	}
	
	@GetMapping("getFacultyById/{id}")
	public ResponseEntity<Faculty> getFacultyById(@PathVariable Integer id) {
		return facultyService.getFacultyById(id);
	}
	
	@GetMapping("auth/{email}/{pass}")
	public ResponseEntity<Faculty> authinticate(@PathVariable String email,@PathVariable("pass") String password){
		return facultyService.authinticate(email,password);
	}
	

	@PutMapping("updateFacultyById/{id}")
	public ResponseEntity<String> updateFacultyById(@PathVariable Integer id, @RequestParam String updFacEnroll,
			@RequestParam String updFacName, @RequestParam String updFacPass, @RequestParam String updFacEmail) {
		Faculty faculty = new Faculty(id,updFacEnroll,updFacEmail,updFacName,updFacPass);
		return facultyService.updateFacultyById(id, faculty);
	}

	@DeleteMapping("deleteFacultyById/{id}")
	public ResponseEntity<Integer> deleteFacultyById(@PathVariable Integer id) {
		return facultyService.deleteFacultyById(id);
	}
}
