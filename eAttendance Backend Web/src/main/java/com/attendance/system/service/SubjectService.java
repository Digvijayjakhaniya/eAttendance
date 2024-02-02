package com.attendance.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.SubjectDao;
import com.attendance.system.model.Subject;

@Service
public class SubjectService {

	@Autowired
	SubjectDao dao;

	public ResponseEntity<String> addSubject(Subject subject) {
		try {
			dao.save(subject);
			return new ResponseEntity<String>("<p class='text-success'>SubjectAdded SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Subject> getSubject(Integer sid) {
		try {
			return new ResponseEntity<Subject>(dao.findById(sid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Subject>> getAllSubjects() {
		try {
			return new ResponseEntity<List<Subject>>(dao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Subject>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<String> updateSubject(Integer sid, String subjectName) {
		try {
			Subject subject = new Subject(sid, subjectName);
			Subject dbSubject = dao.findById(sid).get();
			if (Objects.nonNull(subject.getSubjectName()) && !"".equalsIgnoreCase(subject.getSubjectName())) {
				dbSubject.setSubjectName(subject.getSubjectName());
			}
			dao.save(dbSubject);
			return new ResponseEntity<String>("<p class='text-success'>SubjectUpdated Successfully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> deleteSubject(Integer sid) {
		try {
			dao.deleteById(sid);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}
}
