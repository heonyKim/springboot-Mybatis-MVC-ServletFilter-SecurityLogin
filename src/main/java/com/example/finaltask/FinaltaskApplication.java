package com.example.finaltask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class FinaltaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinaltaskApplication.class, args);
	}

}
