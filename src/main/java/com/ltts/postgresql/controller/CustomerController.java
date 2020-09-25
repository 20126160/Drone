package com.ltts.postgresql.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltts.postgresql.domain.Customer;
import com.ltts.postgresql.domain.CustomerUI;
import com.ltts.postgresql.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository repository;

	@PostMapping("/create")
	public String create(@RequestBody CustomerUI customer) {
		repository.save(new Customer(customer.getFirstName(), customer.getLastName()));
		return "Customer is created";
	}

	@RequestMapping("/search/{id}")
	public String search(@PathVariable long id) {
		String customer = "";
		customer = repository.findById(id).toString();
		return customer;
	}

	@Transactional
	@RequestMapping("/updateFirstName/{oldName}/{newName}")
	public String fetchDataByLastName(@PathVariable String oldName, @PathVariable String newName) {
		repository.updateName(oldName, newName);
		return "Updated Successfully";
	}

	@RequestMapping("/deleteById/{id}")
	public String fetchDataByLastName(@PathVariable long id) {
		repository.deleteById(id);
		return "Deleted Successfully";
	}

}