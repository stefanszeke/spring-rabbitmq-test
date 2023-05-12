package com.example.springamqpintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAmqpIntroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAmqpIntroApplication.class, args);

    System.out.println("\n *** server started *** \n");
	}

}
