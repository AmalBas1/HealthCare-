package com.healthcare.medical_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class MedicalSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicalSystemApplication.class, args);
	}

}
