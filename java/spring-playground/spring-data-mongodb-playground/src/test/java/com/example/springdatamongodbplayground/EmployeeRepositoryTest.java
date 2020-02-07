package com.example.springdatamongodbplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

/**
 * @author DAI Yamasaki
 */
@DataMongoTest
@TestPropertySource(properties = "spring.data.mongodb.port=")
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        final Employee employee = new Employee();
        employee.setName("Taro Yamasaki");
        this.employeeRepository.save(employee);
    }

    @Test
    void save() {
        final Employee employee = new Employee();
        employee.setName("Dai Yamasaki");

        final Employee savedEmployee = this.employeeRepository.save(employee);
        assertThat(savedEmployee.getId()).isNotNull();

    }

    @Test
    void findAll() {
        final Iterable<Employee> all = this.employeeRepository.findAll();
        assertThat(all).isNotEmpty();
    }
}
