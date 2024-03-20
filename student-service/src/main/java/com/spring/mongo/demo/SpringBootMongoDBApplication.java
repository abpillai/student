package com.spring.mongo.demo;

import com.spring.mongo.demo.model.Student;
import com.spring.mongo.demo.utils.HelperUtil;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;


@SpringBootApplication
@EnableMongoRepositories
@OpenAPIDefinition(
		info = @Info(
			title = "Student API", 
			version = "1.0.0", 
			description = "Simple API to handle with Student store"
		)
	)
public class SpringBootMongoDBApplication {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMongoDBApplication.class, args);
	}







	
	/*@Bean
	CommandLineRunner runner() {
		return args -> {
			List<Student> employees = employeeRepository.findAll();
				if (employees.size() == 0) {
					LOGGER.info("******* Inserting Employees to DB *******");
					employeeRepository.saveAll(HelperUtil.employeeSupplier.get());
				} else {
					LOGGER.info("******* Employees stored in DB Size :: {}", employees.size());
					LOGGER.info("******* Employees stored in DB :: {}", employees);
				}

		};
	}*/

}
