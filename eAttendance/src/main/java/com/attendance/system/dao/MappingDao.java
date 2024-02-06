package com.attendance.system.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;

public interface MappingDao extends JpaRepository<Mapping, Integer> {

	List<Mapping> findByCourse(Course course);

}
