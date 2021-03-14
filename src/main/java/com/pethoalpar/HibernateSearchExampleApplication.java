package com.pethoalpar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author alpar.petho
 *
 */
@SpringBootApplication
@EnableAutoConfiguration
public class HibernateSearchExampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateSearchExampleApplication.class, args);
	}

}
