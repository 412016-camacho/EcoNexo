package com.tfi.Econexo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EconexoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EconexoApplication.class, args);
	}

}
