package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Semester;

public interface SemesterService {

	ResponseEntity<String> addSemester(Semester semester);

	ResponseEntity<List<Semester>> getAllSemesters();

	ResponseEntity<Semester> getSemester(Integer sid);

	ResponseEntity<String> updateSemester(Integer sid, String semesterName);

	ResponseEntity<Integer> deleteSemester(Integer sid);

}
