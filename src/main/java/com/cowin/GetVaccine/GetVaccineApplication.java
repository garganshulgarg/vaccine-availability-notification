package com.cowin.GetVaccine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableScheduling
@ImportResource("classpath:beans-context.xml")
public class GetVaccineApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetVaccineApplication.class, args);
	}

}
