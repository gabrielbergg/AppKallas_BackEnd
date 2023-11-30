package com.example.Kallas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@EnableJpaRepositories(basePackages = "com.example.Kallas.repository")
@SpringBootApplication
public class KallasApplication {

	public static void main(String[] args) {
		SpringApplication.run(KallasApplication.class, args);
	}



//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("*")
//				.allowedHeaders("*")
//				.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
//	}

}
