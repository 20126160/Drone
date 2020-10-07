package com.ltts.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.postgresql.domain.Employee;
import com.ltts.postgresql.service.EmployeeService;

@RestController
public class EmployeeController<T> {

	@Autowired
	private EmployeeService<T> EmployeeService;

	// Create Employee
	@PostMapping("/create")
	public String create(@RequestBody Employee Employee) {
		EmployeeService.save(Employee);
		return "Created Successfully";
	}

	// Fetch all the Employees
	@RequestMapping("/findAll")
	public Iterable<Employee> find() {
		return EmployeeService.findAllEmployees();
	}

	// Fetch Employee of a given page
	@GetMapping("/getEmployees/{pageNumber}/{pageSize}/{columnToSort}/{ascending}")
	public List<Employee> fetch(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable T columnToSort,
			@PathVariable boolean ascending) {

		return EmployeeService.getPage(pageNumber, pageSize, columnToSort, ascending);
	}

	// Delete Employee based on ID
	@GetMapping("/deleteById/{id}")
	public String delete(@PathVariable int id) {
		EmployeeService.deleteById(id);
		return "Deleted Successfully";
	}

	// Search Employee based on ID

	@GetMapping("/searchById/{id}")
	public Optional<Employee> search(@PathVariable int id) {
		return EmployeeService.findById(id);
	}

	// Search Employee based on ID

	@GetMapping("/searchByDesignation/{designation}")
	public List<Employee> searchByDesignation(@PathVariable String designation) {
		return EmployeeService.findByDesignation(designation);
	}

	// Update Employee data
	@PutMapping("/updateEmployee")
	public String update(@RequestBody Employee Employee) {
		EmployeeService.update(Employee.getFirstName(), Employee.getId());
		return "Updated Succesfully";
	}

}
