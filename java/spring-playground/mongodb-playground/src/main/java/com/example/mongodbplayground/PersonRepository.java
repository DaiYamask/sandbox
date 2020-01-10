package com.example.mongodbplayground;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

/**
 * @author DAI Yamasaki
 */
public interface PersonRepository extends ReactiveCrudRepository<Person, String> {
}
