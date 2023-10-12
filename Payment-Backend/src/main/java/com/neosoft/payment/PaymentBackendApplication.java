package com.neosoft.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PaymentBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentBackendApplication.class, args);
	}

}
