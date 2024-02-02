package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Course;

public interface CourseService {

	ResponseEntity<String> addCourse(Course course);

	ResponseEntity<List<Course>> getAllCources();

	ResponseEntity<String> updateCourse(Integer cid, String courseName);

	ResponseEntity<Course> getCourse(Integer cid);

	ResponseEntity<Integer> deleteCourse(Integer cid);

	
}
