package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Faculty;

public interface FacultyService {

	ResponseEntity<String> addFaculty(Faculty faculty);

	ResponseEntity<List<Faculty>> getAllFaculty();

	ResponseEntity<Faculty> getFacultyById(Integer fId);

	ResponseEntity<String> updateFacultyById(Integer id, Faculty faculty);

	ResponseEntity<Integer> deleteFacultyById(Integer id);

	ResponseEntity<Faculty> authinticate(String email, String password);

}
