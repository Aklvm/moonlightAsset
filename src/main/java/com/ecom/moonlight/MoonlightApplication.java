package com.ecom.moonlight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan(basePackages = {"com.ecom.moonlight.*"})
@SpringBootApplication
public class MoonlightApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoonlightApplication.class, args);
		System.out.println("Application Started");
	}

}
