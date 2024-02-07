package com.attendance.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.system.model.Course;
import com.attendance.system.model.Semester;
import com.attendance.system.service.HomeService;
import com.attendance.system.service.MappingService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private MappingService mapService;
	
	@SuppressWarnings("null")
	@Override
	public ResponseEntity<?> getAttendanceData() {

		List<Course> courses= mapService.getCourses().getBody();
		
		for(Course course:courses) {
			List<Semester> semesters=mapService.getSemerters(course).getBody();
			
			System.out.print(semesters);
		}
//		TODO : return today's data for attendance

		return null;
	}

}
