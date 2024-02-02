package com.attendance.system.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.attendance.system.model.Attendance;

public interface AttendanceDao extends JpaRepository<Attendance, Integer>{

}
