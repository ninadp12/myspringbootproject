package com.in28minuts.springboot.jpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class UserCommandLineRunner implements CommandLineRunner {

	@Autowired
	UserRepository repository;
	
	private static final Logger log = LoggerFactory
			.getLogger(UserCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		
		repository.save(new User("Ninad", "Admin"));
		repository.save(new User("Rasika", "User"));
		repository.save(new User("Patu", "Admin"));
		repository.save(new User("shruti", "User"));

		for (User user : repository.findAll()) {
			log.info(user.toString());
		}

		log.info("Admin users are.....");
		log.info("____________________");
		for (User user : repository.findByRole("Admin")) {
			log.info(user.toString());
		}
		
	}

}
