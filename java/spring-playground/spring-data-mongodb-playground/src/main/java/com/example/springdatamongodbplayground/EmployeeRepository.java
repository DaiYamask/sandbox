package com.example.springdatamongodbplayground;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * @author DAI Yamasaki
 */
public interface EmployeeRepository extends CrudRepository<Employee, String> {
}
