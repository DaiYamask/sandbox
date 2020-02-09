package com.example.springdatamongodbplayground;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author DAI Yamasaki
 */
@DataMongoTest
@TestPropertySource(properties = "spring.data.mongodb.port=")
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    private Employee savedEmployee;

    @BeforeEach
    void setUp() {
        final Department development = this.departmentRepository.save(new Department("Development"));
        final Department ops = this.departmentRepository.save(new Department("Ops"));
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

    @Test
    void queryByExample() {
        final Employee employee = new Employee("Solid Snake");
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("name", ExampleMatcher.GenericPropertyMatcher::contains);
        final Example<Employee> employeeExample = Example.of(employee, matcher);
        final Iterable<Employee> all = this.employeeRepository.findAll(employeeExample);
        assertThat(all).isNotEmpty();
    }
}
