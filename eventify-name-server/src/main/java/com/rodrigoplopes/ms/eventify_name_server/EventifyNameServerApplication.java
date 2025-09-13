package com.rodrigoplopes.ms.eventify_name_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EventifyNameServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventifyNameServerApplication.class, args);
	}

}
