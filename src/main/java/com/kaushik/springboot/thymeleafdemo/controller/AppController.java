package com.kaushik.springboot.thymeleafdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kaushik.springboot.thymeleafdemo.model.Employee;
import com.kaushik.springboot.thymeleafdemo.services.EmployeeServices;

@Controller
@RequestMapping("/employees")
public class AppController {

	private EmployeeServices employeeServices;
	

	@Autowired
	public AppController(EmployeeServices theEmployeeServices) {
		employeeServices = theEmployeeServices;
	}

	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> employee = employeeServices.findAll();
		model.addAttribute("employee_list", employee);
		return "/employees/list_employees";
	}

	@GetMapping("/addEmployee")
	public String showFormForAddEmployees(Model model) {
		Employee employee = new Employee();
		model.addAttribute("show_employee", employee);
		return "/employees/employee_form";
	}

	@GetMapping("/updateEmployee")
	public String updateEmployeeForm(@RequestParam("employeeId") int Id, Model model) {
		Employee employee = employeeServices.findById(Id);
		model.addAttribute("show_employee", employee);
		return "/employees/employee_form";
	}

	@PostMapping("/save")
	public String saveEmployees(@ModelAttribute("show_employee") Employee employeeModel) {
		employeeServices.save(employeeModel);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		
		// delete the employee
		employeeServices.deleteById(theId);
		
		// redirect to /employees/list
		return "redirect:/employees/list";
		
	}

}
