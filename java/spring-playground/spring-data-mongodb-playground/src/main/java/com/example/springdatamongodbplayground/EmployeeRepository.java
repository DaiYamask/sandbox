package com.example.springdatamongodbplayground;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author DAI Yamasaki
 */
public interface EmployeeRepository extends CrudRepository<Employee, String>, QueryByExampleExecutor<Employee> {
}
