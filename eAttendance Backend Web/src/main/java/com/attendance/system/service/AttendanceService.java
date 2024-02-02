package com.attendance.system.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.AttendanceDao;
import com.attendance.system.model.Attendance;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.Student;

import jakarta.servlet.http.HttpSession;

@Service
public class AttendanceService {

	@Autowired
	AttendanceDao attendanceDao;

	@Autowired
	HttpSession session;

	@Autowired
	MappingService mappingService;

	@Autowired
	StudentService studentService;

	@Autowired
	SessionService sessionService;

	public ResponseEntity<String> addAttendance(Attendance attendance) {
		try {
			attendanceDao.save(attendance);
			return new ResponseEntity<String>("Attendance Marked SuccessFully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Server Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	public ResponseEntity<String> updateAttendance(Integer aid, Boolean isPresent) {
		try {
			Attendance dbAttendance = attendanceDao.findById(aid).get();
			dbAttendance.setIsPresent(isPresent);
			return new ResponseEntity<String>("Attendance Updated SuccessFully", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Server Error: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<Attendance> getAttendance(Integer aid) {
		try {
			return new ResponseEntity<Attendance>(attendanceDao.findById(aid).get(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> startSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		try {
			if (sessionService.createSession(course_id, subject_id, sem_id, division, facultyId)) {
				return new ResponseEntity<String>("Session Started SuccessFully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Could Not Start Session", HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Could Not Start Session: " + e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<String> stopSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId) {
		try {
			if (sessionService.stopSession(course_id, subject_id, sem_id, division, facultyId)) {
				System.out.print("Session Stopped");
				return new ResponseEntity<String>("Session Stopped SuccessFully", HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("There is No Session To Stop", HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>("Could Not Stop Session", HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<Boolean> fillAttendance(Integer mid, Integer sid) {
		try {

			Mapping mapping = mappingService.getMapping(mid).getBody();
			Student student = studentService.getStudent(sid).getBody();
			System.err.println("filling Attendance");
			if (mapping !=null && student !=null && sessionService.isSessionAvailable(mapping.getCourse().getCourseId().toString(),
					mapping.getSubject().getSubjectId().toString(), mapping.getSemester().getSemesterId().toString(),
					student.getStudentDivision(), mapping.getFaculty().getFacultyId().toString())) {
				System.err.println("session is active");
				Attendance attendance = new Attendance();
				attendance.setDateTime(new Date());
				attendance.setIsPresent(true);
				attendance.setMapId(mapping);
				attendance.setStudentId(student);
				attendanceDao.save(attendance);
				return new ResponseEntity<Boolean>(true, HttpStatus.OK);
			} else {
				System.err.println("session is not Active");
				return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return new ResponseEntity<Boolean>(false, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
