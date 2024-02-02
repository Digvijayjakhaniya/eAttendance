package com.attendance.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer studentId;
	private String studentEnrollment;
	private String studentName;
	private String studentEmail;
	private String studentDivision;
	private String studentPassword;
	@ManyToOne
	private Course studentCourse;
	@ManyToOne
	private Batch studentBatch;
}
