package com.spring.mongo.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.mongo.demo.model.Student;

import java.util.List;

@SuppressWarnings("unused")
@Repository
public interface StudentRepository extends MongoRepository<Student, String> {}
