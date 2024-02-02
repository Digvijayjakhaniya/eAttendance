package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Mapping;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;

public interface StudentService {

	ResponseEntity<StudentWrapper> getAll();

	ResponseEntity<List<Student>> getAllStudents();

	ResponseEntity<String> addStudent(Student student);

	ResponseEntity<Student> authenticate(String email, String password);

	ResponseEntity<List<String>> getDivisons();

	ResponseEntity<Mapping> isSession(Integer sid);

	ResponseEntity<Student> getStudent(Integer sid);

}
