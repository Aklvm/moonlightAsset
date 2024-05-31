package com.ecom.moonlight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.ecom.moonlight.logging.Mlogger;
@ComponentScan(basePackages = {"com.ecom.moonlight.*"})
@SpringBootApplication
public class MoonlightApplication {

	public static void main(String[] args) {

		try{
		SpringApplication.run(MoonlightApplication.class, args);
		System.out.println("Application Started");
		}catch(Exception e){
			Mlogger mlogger=Mlogger.getlogger(MoonlightApplication.class);
			mlogger.error("Application Failed to Start",e);
			System.out.println("Application Failed to Start");
		}
	}

}
