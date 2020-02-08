package com.example.springdatamongodbplayground;


import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author DAI Yamasaki
 */
@DataMongoTest
@TestPropertySource(properties = "spring.data.mongodb.port=")
public class MongoTemplateTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void createCollection() {
		final MongoCollection<Document> collection = this.mongoTemplate.createCollection(Employee.class);
		assertThat(collection).isNotNull();
	}

	@Test
	void createDocument() {
		final Employee employee = this.mongoTemplate.insert(new Employee("Raiden"));
		assertThat(employee.getId()).isNotNull();
	}
}
