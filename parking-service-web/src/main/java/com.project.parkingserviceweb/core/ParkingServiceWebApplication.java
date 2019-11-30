package com.project.parkingserviceweb.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = {"com.project.parkingserviceweb.*"})
//@EntityScan(value = "com.project.parkingserviceweb.persistence.sql.entities")
//@EnableJpaRepositories(value = "com.project.parkingserviceweb.persistence.sql.repositories")
public class ParkingServiceWebApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ParkingServiceWebApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ParkingServiceWebApplication.class, args);
	}

}
