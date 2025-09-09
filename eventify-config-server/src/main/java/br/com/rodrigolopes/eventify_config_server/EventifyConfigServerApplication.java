package br.com.rodrigolopes.eventify_config_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EventifyConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventifyConfigServerApplication.class, args);
	}

}
