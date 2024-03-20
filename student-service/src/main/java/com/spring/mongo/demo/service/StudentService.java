package com.spring.mongo.demo.service;


import java.util.List;

import com.spring.mongo.demo.model.Student;

public interface StudentService {
	
	List<Student> getAll();

	Student getStudentByFirstName(String firstName);

	Student getStudentByLastName(String lastName);

	Student getStudentByDepName(String firstName);

	

}
