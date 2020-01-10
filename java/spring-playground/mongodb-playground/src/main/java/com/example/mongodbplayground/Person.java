package com.example.mongodbplayground;

import lombok.Getter;

/**
 * @author DAI Yamasaki
 */
@Getter
public class Person {

    private String id;
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person [id= " + id + "name= " + name + ", age= " + age + "]";
    }
}
