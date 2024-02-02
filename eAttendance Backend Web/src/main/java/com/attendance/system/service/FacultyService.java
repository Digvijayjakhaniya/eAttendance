package com.attendance.system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.FacultyDao;
import com.attendance.system.model.Faculty;

@Service
public class FacultyService {

	@Autowired
	FacultyDao facultyDao;

	public ResponseEntity<String> addFaculty(Faculty faculty) {
		try {
			facultyDao.save(faculty);
			return new ResponseEntity<>("<p class='text-success'>Faculty Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("<p class='text-danger'>" + e.getMessage() + "</p>", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<List<Faculty>> getAllFaculty() {
		try {
			return new ResponseEntity<List<Faculty>>(facultyDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Faculty>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Faculty> getFacultyById(Integer fId) {
		try {
			return new ResponseEntity<Faculty>(facultyDao.findById(fId).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> updateFacultyById(Integer id, Faculty faculty) {
		try {
			Faculty facDB = facultyDao.findById(id).get();
			if (Objects.nonNull(faculty.getFacultyName()) && !"".equalsIgnoreCase(faculty.getFacultyName())) {
				facDB.setFacultyName(faculty.getFacultyName());
			}
			if (Objects.nonNull(faculty.getFacultyEmail()) && !"".equalsIgnoreCase(faculty.getFacultyEmail())) {
				facDB.setFacultyEmail(faculty.getFacultyEmail());
			}
			if (Objects.nonNull(faculty.getFacultyEnrollment())
					&& !"".equalsIgnoreCase(faculty.getFacultyEnrollment())) {
				facDB.setFacultyEnrollment(faculty.getFacultyEnrollment());
			}
			if (Objects.nonNull(faculty.getFacultyPassword()) && !"".equalsIgnoreCase(faculty.getFacultyPassword())) {
				facDB.setFacultyPassword(faculty.getFacultyPassword());
			}
			facultyDao.save(facDB);
			return new ResponseEntity<String>("<p class='text-success'>Faculty Updated SuccessFully</p>",
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>Unknown Error[<small>" + e.getMessage()
					+ "</small>] In Updating faculty</p>", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Integer> deleteFacultyById(Integer id) {
		try {
			facultyDao.deleteById(id);
			return new ResponseEntity<Integer>(1, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(0, HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Faculty> authinticate(String email, String password) {
		Faculty faculty = facultyDao.findByFacultyEmailAndFacultyPassword(email, password);
		if (faculty != null) {
			return new ResponseEntity<Faculty>(faculty, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
