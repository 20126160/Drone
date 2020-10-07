package com.ltts.postgresql.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ltts.postgresql.domain.Employee;
import com.ltts.postgresql.repository.EmployeeRepository;

@Service
public class EmployeeService<T> {

	@Autowired
	EmployeeRepository repository;

	public void save(Employee Employee) {
		repository.save(Employee);
	}

	public void deleteById(Integer id) {
		repository.deleteById(id);
	}

	public Iterable<Employee> findAllEmployees() {
		return repository.findAll();
	}

	public List<Employee> getPage(int pageNumber, int pageSize, T columnToSort, boolean ascending) {
		PageRequest pageable = null;
		if (ascending) {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(columnToSort.toString()));
		} else {
			pageable = PageRequest.of(pageNumber, pageSize, Sort.by(columnToSort.toString()).descending());
		}

		return repository.findAll(pageable).getContent();

	}

	public Optional<Employee> findById(Integer id) {
		return repository.findById(id);
	}

	@Transactional
	public void update(String firstName, Integer empId) {
		repository.update(empId, firstName);
	}

	public List<Employee> findByDesignation(String designation) {
		return repository.fetchByDesignation(designation);
	}
}
