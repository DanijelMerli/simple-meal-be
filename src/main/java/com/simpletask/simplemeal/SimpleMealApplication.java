package com.simpletask.simplemeal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SimpleMealApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleMealApplication.class, args);
	}

}
