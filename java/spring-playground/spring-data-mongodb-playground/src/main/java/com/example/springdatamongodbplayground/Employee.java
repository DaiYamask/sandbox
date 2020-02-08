package com.example.springdatamongodbplayground;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author DAI Yamasaki
 */
@Document
public class Employee {
    @Id
    String id;
    String name;
    int age;
    @DBRef
    Department department;

    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age, Department department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
