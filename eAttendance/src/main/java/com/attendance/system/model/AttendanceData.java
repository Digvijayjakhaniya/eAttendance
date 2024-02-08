package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceData {
	private Course course;
	private List<Semester> semesters;
	private List<SubjectWrapper> subjectWrapper;
}
