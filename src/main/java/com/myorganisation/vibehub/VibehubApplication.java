package com.myorganisation.vibehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VibehubApplication {

	public static void main(String[] args) {
		SpringApplication.run(VibehubApplication.class, args);
	}

}
