package com.example.springdatamongodbplayground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author DAI Yamasaki
 */
@DataMongoTest
@TestPropertySource(properties = "spring.data.mongodb.port=")
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void save() {
        final Employee employee = new Employee();
        employee.setName("Dai Yamasaki");

        final Employee savedEmployee = this.employeeRepository.save(employee);
        assertThat(savedEmployee.getId()).isNotNull();

    }
}