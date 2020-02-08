package com.example.springdatamongodbplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

/**
 * @author DAI Yamasaki
 */
@DataMongoTest
@TestPropertySource(properties = "spring.data.mongodb.port=")
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee savedEmployee;

    @BeforeEach
    void setUp() {
        final Department development = new Department("Development");
        final Department ops = new Department("Ops");
        final Employee employee = new Employee("Solid Snake", 27, ops);
        this.savedEmployee = this.employeeRepository.save(employee);
        this.employeeRepository.save(new Employee("Solidus Snake", 42, development));
    }

    @Test
    void save() {
        final Employee employee = new Employee("Dai Yamasaki");
        employee.setName("Dai Yamasaki");

        final Employee savedEmployee = this.employeeRepository.save(employee);
        assertThat(savedEmployee.getId()).isNotNull();

    }

    @Test
    void findAll() {
        final Iterable<Employee> all = this.employeeRepository.findAll();
        assertThat(all).isNotEmpty();
    }

    @Test
    void findById() {
        final Optional<Employee> employee = this.employeeRepository.findById(savedEmployee.getId());
        assertThat(employee.get()).isNotNull();
        assertThat(employee.get().getId()).isEqualTo(this.savedEmployee.getId());
    }
}
