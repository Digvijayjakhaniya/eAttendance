package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Faculty;

public interface FacultyDao extends JpaRepository<Faculty,Integer>{

	Faculty findByFacultyEmailAndFacultyPassword(String email, String password);

}
