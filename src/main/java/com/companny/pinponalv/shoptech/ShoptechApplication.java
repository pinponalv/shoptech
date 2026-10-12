package com.companny.pinponalv.shoptech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //permite tener tareas automatizadas
public class ShoptechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoptechApplication.class, args);
	}

}
