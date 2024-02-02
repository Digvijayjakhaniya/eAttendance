package com.attendance.system.service;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Attendance;

public interface AttendanceService {

	public ResponseEntity<String> addAttendance(Attendance attendance);
	public ResponseEntity<String> updateAttendance(Integer aid, Boolean isPresent);
	public ResponseEntity<Attendance> getAttendance(Integer aid);
	public ResponseEntity<String> startSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId);
	ResponseEntity<String> stopSession(String course_id, String subject_id, String sem_id, String division,
			String facultyId);
	ResponseEntity<Boolean> fillAttendance(Integer mid, Long sid);
}
