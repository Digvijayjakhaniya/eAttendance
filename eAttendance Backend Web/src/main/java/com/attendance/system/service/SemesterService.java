package com.attendance.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.SemesterDao;
import com.attendance.system.model.Semester;

@Service
public class SemesterService {

	@Autowired
	SemesterDao dao;

	public ResponseEntity<String> addSemester(Semester semester) {
		try {
			dao.save(semester);
			return new ResponseEntity<String>("<p class='text-success'>Semester Added Successfully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p>" + e.getMessage() + "</p>", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Semester>> getAllSemesters() {
		try {
			return new ResponseEntity<List<Semester>>(dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Semester>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Semester> getSemester(Integer sid) {
		try {
			return new ResponseEntity<Semester>(dao.findById(sid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> updateSemester(Integer sid, String semesterName) {
		try {
			Semester sem = new Semester(sid, semesterName);
			Semester dbSem = dao.findById(sid).get();
			if (Objects.nonNull(sem.getSemesterName()) && !"".equalsIgnoreCase(sem.getSemesterName())) {
				dbSem.setSemesterName(sem.getSemesterName());
			}
			dao.save(dbSem);
			return new ResponseEntity<String>("<p class='text-success'>Semester Updated SuccessFully</p>",
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class'text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> deleteSemester(Integer sid) {
		try {
			dao.deleteById(sid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
