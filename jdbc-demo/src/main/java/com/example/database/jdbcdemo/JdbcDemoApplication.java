package com.example.database.jdbcdemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.database.jdbcdemo.dao.PersonDao;
import com.example.database.jdbcdemo.entity.Person;

@SpringBootApplication
public class JdbcDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	PersonDao dao;

	public static void main(String[] args) {
		SpringApplication.run(JdbcDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		logger.info("All users {} ", dao.findAll());

		logger.info("User with specific id {}", dao.findById(1));

		logger.info("Users are located at Giza {}", dao.findByLocation("Giza", "Ahmed"));

		logger.info("Number of deleted users {}", dao.deleteById(2));

		logger.info("Insert new user {}", dao.insert(new Person(4, "Attia", "Giza", new Date(19600101000000L))));

		logger.info("Update user {}", dao.update(new Person(1, "Mido", "Giza", new Date(20000101000000L))));
		
		logger.info("All users {} ", dao.findAll());


	}

}
