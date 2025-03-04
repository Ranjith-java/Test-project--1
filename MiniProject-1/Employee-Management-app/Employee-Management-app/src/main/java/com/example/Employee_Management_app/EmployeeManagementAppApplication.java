package com.example.Employee_Management_app;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *  Employee Management Application project
 *  controller layer created to manage the endpoints
 *  service layer created to do the process of CRUD
 *  repository layer created to actively response between user and database
 */
@SpringBootApplication
public class EmployeeManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementAppApplication.class, args);
	}

}
