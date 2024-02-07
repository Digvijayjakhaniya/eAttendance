package com.attendance.system.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.attendance.system.enums.Role;
import com.attendance.system.model.Batch;
import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.service.StudentService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping
	public ModelAndView student() {
		return new ModelAndView("student").addObject("studentWrapper", studentService.getAll().getBody());
	}

	@PostMapping("add")
	public ResponseEntity<String> addStudent(@RequestParam("userName") String username,
			@RequestParam("email") String email, @RequestParam("enrollment") String enrollment,
			@RequestParam("studentDivision") String studentDivision, @PathParam("password") String password,
			@RequestParam("studentCourse") Course course, @RequestParam("studentBatch") Batch batch) {
		SiteUser user = SiteUser.builder().userName(username).email(email).password(password).enrollment(enrollment)
				.role(Role.STUDENT).build();
		Student student = Student.builder().studentBatch(batch).studentDivision(studentDivision).studentCourse(course)
				.build();
		return studentService.addStudent(student, user);
	}

	@GetMapping("getDivisions")
	public ResponseEntity<List<String>> getDivisions() {
		return studentService.getDivisons();
	}

	@GetMapping("isSession/{sid}")
	public ResponseEntity<Mapping> isSession(@PathVariable @NonNull Long sid) {
		return studentService.isSession(sid);

	}

	@GetMapping("getStudent/{sid}")
	public ResponseEntity<Student> getStudent(@PathVariable Long sid) {
		return studentService.getStudent(sid);
	}

	@PutMapping("updateStudent")
	public ResponseEntity<String> updateStudentById(@RequestParam Long updStuId,@RequestParam Long updUserId, @RequestParam String updStuEnroll,
			@RequestParam String updStuName, @RequestParam(required = false) String updStuEmail,
			@RequestParam String updStuDivision, @RequestParam Course updStuCourse, @RequestParam Batch updStuBatch) {

		SiteUser user = SiteUser.builder().userId(updUserId).userName(updStuName).email(updStuEmail).enrollment(updStuEnroll)
				.build();
		Student student = Student.builder().studentId(updStuId).studentDivision(updStuDivision).studentCourse(updStuCourse)
				.studentBatch(updStuBatch).user(user).build();
		studentService.updateStudent(student);
		return ResponseEntity.ok("Faculty Updated Successfully");

	}

}
