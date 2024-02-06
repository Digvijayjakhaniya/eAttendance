package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;

public interface StudentService {

	ResponseEntity<StudentWrapper> getAll();

	ResponseEntity<List<Student>> getAllStudents();

	ResponseEntity<String> addStudent(Student student,SiteUser user);

	ResponseEntity<List<String>> getDivisons();

	ResponseEntity<Mapping> isSession(@NonNull Long sid);

	ResponseEntity<Student> getStudent(@NonNull Long sid);
	
	ResponseEntity<Student> getStudent(SiteUser user);

}
