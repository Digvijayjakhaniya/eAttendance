package com.attendance.system.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectWrapper {
	private Semester semester;
	private List<Subject> subjects;
}