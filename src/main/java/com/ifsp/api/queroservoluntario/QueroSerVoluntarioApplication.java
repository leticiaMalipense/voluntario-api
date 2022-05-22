package com.ifsp.api.queroservoluntario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QueroSerVoluntarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueroSerVoluntarioApplication.class, args);
	}
}
