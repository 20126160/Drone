package com.ltts.postgresql.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ltts.postgresql.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	@Modifying
	@Query(value = "update customer set firstname=:newName where firstname=:oldName", nativeQuery = true)
	Integer updateName(@Param("oldName") String oldName, @Param("newName") String newName);

}