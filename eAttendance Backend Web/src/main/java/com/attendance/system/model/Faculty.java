package com.attendance.system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"facultyEnrollment"}))
public class Faculty {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer facultyId;
	private String facultyEnrollment;
	private String facultyEmail;
	private String facultyName;
	private String facultyPassword;
}
