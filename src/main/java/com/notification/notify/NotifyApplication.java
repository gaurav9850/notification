package com.notification.notify;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class NotifyApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotifyApplication.class, args);
		System.out.println("======================================");
		System.out.println("NOTIFICATION SERVICE STARTED");
		System.out.println("======================================");
	}

}
