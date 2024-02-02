package com.attendance.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.CourseDao;
import com.attendance.system.model.Course;

@Service
public class CourseService {

	@Autowired
	CourseDao courseDao;

	public ResponseEntity<String> addCourse(Course course) {
		try {
			courseDao.save(course);
			return new ResponseEntity<String>("<p class='text-success'>Faculty Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Course>> getAllCources() {
		try {
			return new ResponseEntity<List<Course>>(courseDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Course>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> updateCourse(Integer cid, String courseName) {
		try {
			Course course = new Course(cid, courseName);
			Course dbCourse = courseDao.findById(cid).get();

			if (Objects.nonNull(course.getCourseName()) && !"".equalsIgnoreCase(course.getCourseName())) {
				dbCourse.setCourseName(course.getCourseName());
			}
			courseDao.save(dbCourse);
			return new ResponseEntity<String>("<p class='text-success'>Course Updated SuccessFully</p>", HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Course> getCourse(Integer cid) {
		try {
			return new ResponseEntity<Course>(courseDao.findById(cid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Integer> deleteCourse(Integer cid) {
		try {
			courseDao.deleteById(cid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
