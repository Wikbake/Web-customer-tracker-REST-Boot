package com.github.wikbake.springboot.cruddemo.service;

import java.util.List;

import com.github.wikbake.springboot.cruddemo.entity.Employee;

public interface EmployeeService {

public List<Employee> findAll();
	
	public Employee findById(int theId);
	
	public void save(Employee theEmployee);
	
	public void deleteById(int theId);
	
	
}
