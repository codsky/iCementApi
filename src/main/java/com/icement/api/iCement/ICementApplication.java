package com.icement.api.iCement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.icement.api.iCement")
public class ICementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ICementApplication.class, args);
	}

}
