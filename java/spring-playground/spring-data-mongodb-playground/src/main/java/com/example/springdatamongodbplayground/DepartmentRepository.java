package com.example.springdatamongodbplayground;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

/**
 * @author DAI Yamasaki
 */
public interface DepartmentRepository extends CrudRepository<Department, String>, QueryByExampleExecutor<Department> {
}
