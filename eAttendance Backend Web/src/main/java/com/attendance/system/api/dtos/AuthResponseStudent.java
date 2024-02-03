package com.attendance.system.api.dtos;

import java.util.Date;

import com.attendance.system.model.Batch;
import com.attendance.system.model.Course;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponseStudent {
	private final String token;
	private final Date expiry;
	private final String username;
	private final String email;
	private final String enrollment;
	private final String division;
	private final Course course;
	private final Batch batch;
	private final Long studentId;

}
