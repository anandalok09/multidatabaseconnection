package com.quese.queseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableFeignClients
public class QueseserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(QueseserviceApplication.class, args);
	}

}
