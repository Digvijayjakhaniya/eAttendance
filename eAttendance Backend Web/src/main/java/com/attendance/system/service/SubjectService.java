package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Subject;

public interface SubjectService {

	ResponseEntity<String> addSubject(Subject subject);

	ResponseEntity<Subject> getSubject(Integer sid);

	ResponseEntity<List<Subject>> getAllSubjects();

	ResponseEntity<String> updateSubject(Integer sid, String subjectName);

	ResponseEntity<Integer> deleteSubject(Integer sid);

}
