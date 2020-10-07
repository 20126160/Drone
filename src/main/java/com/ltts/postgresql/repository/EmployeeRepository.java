package com.ltts.postgresql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.ltts.postgresql.domain.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer> {

	@Modifying
	@Query(value = "Update Employee set firstName= :firstName where id= :id", nativeQuery = true)
	void update(@Param("id") Integer id, @Param("firstName") String firstName);

	@Query("From Employee where designation= :designation")
	List<Employee> fetchByDesignation(@Param("designation") String designation);

}
