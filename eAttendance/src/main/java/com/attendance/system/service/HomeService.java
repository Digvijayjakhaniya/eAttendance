package com.attendance.system.service;

import org.springframework.http.ResponseEntity;

public interface HomeService {
	
	ResponseEntity<?> getAttendanceData();
	
}
