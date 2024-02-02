package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.attendance.system.model.Student;

public interface StudentDao extends JpaRepository<Student, Integer>{

	Student findByStudentEmailAndStudentPassword(String email, String password);

	@Query(value="SELECT DISTINCT student_division FROM STUDENT",nativeQuery=true)
	List<String> getDivisons();

}
