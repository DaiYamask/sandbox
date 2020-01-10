package com.example.mongodbplayground;

import com.mongodb.client.MongoClients;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

//@SpringBootApplication
@Log4j2
public class MongodbPlaygroundApplication {

	public static void main(String[] args) {
//		SpringApplication.run(MongodbPlaygroundApplication.class, args);
		final MongoTemplate mongoOps = new MongoTemplate(MongoClients.create(), "mongodb-playground");
//		mongoOps.insert(new Person("Dai", 34));

		log.info(mongoOps.findOne(new Query(where("name").is("Dai")), Person.class));

	}

}
