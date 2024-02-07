package com.attendance.system.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;

import com.attendance.system.model.Course;
import com.attendance.system.model.Mapping;
import com.attendance.system.model.MappingWrapper;

public interface MappingService {

	ResponseEntity<MappingWrapper> getAll();

	ResponseEntity<String> addMapping(@NonNull Mapping mapping);

	ResponseEntity<Integer> deleteMapping(@NonNull Integer mid);

	ResponseEntity<Mapping> getMapping(Integer mid);

	List<Mapping> getMappingsFor(Course course);

}
