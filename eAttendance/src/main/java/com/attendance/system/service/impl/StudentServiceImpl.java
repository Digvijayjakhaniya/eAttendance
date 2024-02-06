package com.attendance.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.StudentDao;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.SiteUser;
import com.attendance.system.model.Student;
import com.attendance.system.model.StudentWrapper;
import com.attendance.system.service.BatchService;
import com.attendance.system.service.CourseService;
import com.attendance.system.service.MappingService;
import com.attendance.system.service.SessionService;
import com.attendance.system.service.StudentService;
import com.attendance.system.service.UserService;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private CourseService courseService;

	@Autowired
	private MappingService mappingService;

	@Autowired
	private BatchService batchService;
	
	@Autowired
	private SessionService sessionService;
	
	@Autowired
	private UserService userService;

	@SuppressWarnings("null")
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
	public ResponseEntity<String> addStudent(Student student,SiteUser user) {
		try {
			student.setUser(userService.addUser(user).getBody());
			studentDao.save(student);
			return new ResponseEntity<String>("<p class='text-success'>Student Added SuccessFully</p>", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("<p class='text-danger'>" + e.getMessage() + "</p>",
					HttpStatus.BAD_REQUEST);
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

	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Mapping> isSession(@NonNull Long sid) {
		try {
			Student student = studentDao.findById(sid).get();
			String course_id = student.getStudentCourse().getCourseId().toString();
			String division = student.getStudentDivision();
			List<Mapping> mappings = mappingService.getMappingsFor(courseService.getCourse(Integer.parseInt(course_id)).getBody());
//			return new ResponseEntity<Mapping>(mappings.get(0),HttpStatus.OK);
			for (Mapping mapping : mappings) {
				String facultyId = mapping.getFaculty().getUserId().toString();
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
	
	@SuppressWarnings("null")
	@Override
	public ResponseEntity<Student> getStudent(@NonNull Long sid){
		try {
			return new ResponseEntity<Student>(studentDao.findById(sid).get(),HttpStatus.OK);	
		}catch (Exception e) {
			return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
		}
		
	}

	@Override
	public ResponseEntity<Student> getStudent(SiteUser user) {
		return ResponseEntity.ok(studentDao.findByUser(user));
	}

}
