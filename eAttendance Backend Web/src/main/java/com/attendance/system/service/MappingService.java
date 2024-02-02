package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.MappingWrapper;

public interface MappingService {

	ResponseEntity<MappingWrapper> getAll();

	ResponseEntity<String> addMapping(Mapping mapping);

	ResponseEntity<Integer> deleteMapping(Integer mid);

	ResponseEntity<Mapping> getMapping(Integer mid);

	List<Mapping> getMappingsFor(Course course);

}
