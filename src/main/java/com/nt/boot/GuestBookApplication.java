package com.nt.boot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages= {"com.nt.dao","com.nt.service","com.nt.controller"})
@EnableJpaRepositories(basePackages= {"com.nt.dao"})
@EntityScan(value= {"com.nt.model"})
public class GuestBookApplication {
	
	// Logger logger=LoggerFactory.getLogger(GuestBookApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GuestBookApplication.class, args);
	}
}
