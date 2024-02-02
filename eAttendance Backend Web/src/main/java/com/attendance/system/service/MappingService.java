package com.attendance.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.MappingDao;
import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.MappingWrapper;

@Service
public class MappingService {

	@Autowired
	CourseService courseService;

	@Autowired
	SubjectService subjectService;

	@Autowired
	SemesterService semesterService;

	@Autowired
	FacultyService facultyService;

	@Autowired
	MappingDao mappingDao;

	public ResponseEntity<MappingWrapper> getAll() {
		try {
			MappingWrapper mappingWrapper = new MappingWrapper(courseService.getAllCources().getBody(),
					subjectService.getAllSubjects().getBody(), semesterService.getAllSemesters().getBody(),
					facultyService.getAllFaculty().getBody(), mappingDao.findAll());
			return new ResponseEntity<MappingWrapper>(mappingWrapper, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> addMapping(Mapping mapping) {
		try {
			mappingDao.save(mapping);
			return new ResponseEntity<String>("<p class='text-success'>Mapping Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> deleteMapping(Integer mid) {
		try {
			mappingDao.deleteById(mid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<Mapping> getMapping(Integer mid){
		try {
			return new ResponseEntity<Mapping>(mappingDao.findById(mid).get(),HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
	}

	public List<Mapping> getMappingsFor(Course course) {
		return mappingDao.findByCourse(course);
	}
}
