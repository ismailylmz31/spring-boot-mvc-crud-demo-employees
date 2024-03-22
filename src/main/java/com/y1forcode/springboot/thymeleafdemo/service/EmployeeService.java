package com.y1forcode.springboot.thymeleafdemo.service;

import java.util.List;

import com.y1forcode.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	Employee findById(int theId);
	
	void save(Employee theEmployee);
	
	void deleteById(int theId);
	
}
