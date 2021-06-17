package com.oracle.tech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.oracle.tech.*")
@SpringBootApplication
public class OracleTechChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(OracleTechChallengeApplication.class, args);
	}

}
