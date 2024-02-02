package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.StudentDao;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;
import com.attendance.system.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private CourseServiceImpl courseService;

	@Autowired
	private MappingServiceImpl mappingService;

	@Autowired
	private BatchServiceImpl batchService;
	
	@Autowired
	private SessionServiceImpl sessionService;

	@Override
	public ResponseEntity<StudentWrapper> getAll() {
		try {
			StudentWrapper studentWrapper = new StudentWrapper(getAllStudents().getBody(),
					batchService.getAllBatches().getBody(), courseService.getAllCources().getBody());
			return new ResponseEntity<StudentWrapper>(studentWrapper, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<Student>> getAllStudents() {
		try {
			return new ResponseEntity<List<Student>>(studentDao.findAll(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Student>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<String> addStudent(Student student) {
		try {
			studentDao.save(student);
			return new ResponseEntity<String>("<p class='text-success'>Student Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public ResponseEntity<Student> authenticate(String email, String password) {
		try {
			return new ResponseEntity<Student>(studentDao.findByStudentEmailAndStudentPassword(email, password),
					HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<List<String>> getDivisons() {
		try {

			return new ResponseEntity<List<String>>(studentDao.getDivisons(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<String>>(new ArrayList<>(), HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Mapping> isSession(Integer sid) {
		try {
			Student student = studentDao.findById(sid).get();
			String course_id = student.getStudentCourse().getCourseId().toString();
			String division = student.getStudentDivision();
			List<Mapping> mappings = mappingService.getMappingsFor(courseService.getCourse(Integer.parseInt(course_id)).getBody());
//			return new ResponseEntity<Mapping>(mappings.get(0),HttpStatus.OK);
			for (Mapping mapping : mappings) {
				String facultyId = mapping.getFaculty().getFacultyId().toString();
				String sem_id = mapping.getSemester().getSemesterId().toString();
				String subject_id = mapping.getSubject().getSubjectId().toString();
				String sessionId = course_id + "-" + subject_id + "-" + sem_id + "-" + division + "-" + facultyId;
				System.err.println(sessionId);
				if (sessionService.isSessionAvailable(course_id, subject_id, sem_id, division,facultyId)) {
					System.err.println("Session is Active");
					return new ResponseEntity<Mapping>(mapping,HttpStatus.OK);
				}
			}
			return null;
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
	}
	
	@Override
	public ResponseEntity<Student> getStudent(Integer sid){
		try {
			return new ResponseEntity<Student>(studentDao.findById(sid).get(),HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
	}

}
