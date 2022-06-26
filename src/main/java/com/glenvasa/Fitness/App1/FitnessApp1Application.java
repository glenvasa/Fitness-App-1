package com.glenvasa.Fitness.App1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FitnessApp1Application {

	private static final Logger logger = LoggerFactory.getLogger(FitnessApp1Application.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(FitnessApp1Application.class, args);
		} catch (Exception e){
			System.out.println("The following error occurred when attempting to start/run the application: " + e.getMessage());
			logger.error("Error occurred when attempting to start/run the application");
			}

	}

}
