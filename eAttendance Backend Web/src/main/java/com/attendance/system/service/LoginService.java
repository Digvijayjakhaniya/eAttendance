package com.attendance.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.system.dao.AdminDao;
import com.attendance.system.dao.FacultyDao;
import com.attendance.system.model.Admin;
import com.attendance.system.model.Faculty;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Service
public class LoginService {

	@Autowired
	AdminDao adminDao;

	@Autowired
	FacultyDao facultyDao;

	public String authenticate(String email, String password, String loginType, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			if (loginType.equalsIgnoreCase("Admin")) {
				Admin admin = adminDao.findByEmailAndPassword(email, password).get();
				if (admin == null)
					return "login?error=true";
				session.setAttribute("admin", admin);
			}
			if (loginType.equalsIgnoreCase("Faculty")) {
				Faculty faculty = facultyDao.findByFacultyEmailAndFacultyPassword(email, password);
				if (faculty == null)
					return "login?error=true";
				session.setAttribute("faculty", faculty);
			}
			session.setMaxInactiveInterval(0);
			if (request.getParameter("remember-me") != null) {
				Cookie cookie = new Cookie("email", email);
				response.addCookie(cookie);
				cookie = new Cookie("password", password);
				response.addCookie(cookie);
				cookie = new Cookie("loginType", loginType);
				response.addCookie(cookie);
			} else {
				response.addCookie(new Cookie("email", ""));
				response.addCookie(new Cookie("password", ""));
				response.addCookie(new Cookie("loginType", ""));
			}
			return "home";
		} catch (Exception e) {
			return null;
		}
	}

}
