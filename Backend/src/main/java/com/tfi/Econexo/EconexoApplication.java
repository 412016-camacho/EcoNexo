package com.tfi.Econexo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
public class EconexoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconexoApplication.class, args);
	}

}
