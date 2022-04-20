package com.kaushik.springboot.thymeleafdemo.services;

import java.util.List;

import com.kaushik.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeServices {

	public List<Employee> findAll();

	public Employee findById(int theId);

	public void save(Employee theEmployee);

	public void deleteById(int theId);

}
