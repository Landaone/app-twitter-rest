package com.llandaeta.twitter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.llandaeta.twitter.core.services.DataTwitterPopulateService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@SpringBootApplication
public class Application implements CommandLineRunner {
	private DataTwitterPopulateService populateService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	/**
	 * execution of the service that connects with the twitter API to obtain the data
	 */
	@Bean
	public CommandLineRunner streamingAPI() {
		return args -> 	populateService.streamingAPI();
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
