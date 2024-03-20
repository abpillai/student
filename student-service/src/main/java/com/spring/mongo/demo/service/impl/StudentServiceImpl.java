package com.spring.mongo.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.mongo.demo.model.Student;
import com.spring.mongo.demo.repository.StudentRepository;
import com.spring.mongo.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	

	@Autowired
	private StudentRepository repository;
	

	
	@Override
	public List<Student> getAll() {
		return repository.findAll();
	}
	
	@Override
	public Student getStudentByLastName(String lastName) {
		
		List<Student> Students = repository.findAll();
		
		for (Student emp : Students) {
			if (emp.getLastName().equalsIgnoreCase(lastName))
				return emp;
		}
		Student student = new Student();
		student.setFirstName("Not Found");
		student.setLastName("Please enter valid value");
		return student;
	}
	
	@Override
	public Student getStudentByDepName(String lastName) {
		
		List<Student> Students = repository.findAll();
		
		for (Student emp : Students) {
			if (emp.getDepName().equalsIgnoreCase(lastName))
				return emp;
		}
		Student student = new Student();
		student.setFirstName("Not Found");
		student.setLastName("Please enter valid value");
		return student;
	}

	

	@Override
	public Student getStudentByFirstName(String lastName) {
		
		List<Student> Students = repository.findAll();
		
		for (Student emp : Students) {
			if (emp.getLastName().equalsIgnoreCase(lastName))
				return emp;
		}
		Student student = new Student();
		student.setFirstName("Not Found");
		student.setLastName("Please enter valid value");
		return student;
	}


	
	

	
	
	
	

}
