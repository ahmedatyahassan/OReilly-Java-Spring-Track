package com.database.jpademo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.database.jpademo.dao.PersonDao;
import com.database.jpademo.entity.Person;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Autowired
	PersonDao dao;

	@Override
	public void run(String... args) throws Exception {

		Person person = new Person();
		person = dao.insert(new Person("Ahmed", "Giza", new Date()));

		logger.info("insert new user {}", person);

		logger.info("find user with id = 1 {}", dao.findById(person.getId()));

		logger.info("Update user with id = 1 {}", dao.update(new Person(1, "Attia", "Cairo", new Date())));

		logger.info("find all users {}", dao.findAll());

		dao.deleteById(person.getId());

	}

}
