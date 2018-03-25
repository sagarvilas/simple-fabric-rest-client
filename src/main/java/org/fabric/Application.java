package org.fabric;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application {

	public static void main(String a[]) {
		SpringApplication.run(Application.class, a);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
