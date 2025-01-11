package com.crudApplication.crudApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.crudApplication.crudApp")
@EnableJpaRepositories(basePackages = "com.crudApplication.crudApp.Repository")
@EntityScan(basePackages = "com.crudApplication.crudApp.Entity")
public class CrudAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudAppApplication.class, args);
	}

}
