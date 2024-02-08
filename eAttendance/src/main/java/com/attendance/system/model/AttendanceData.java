package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceData {
	private List<Subject> stujects;
	private List<Integer> subjectAttendance;
	private Integer total;
}
