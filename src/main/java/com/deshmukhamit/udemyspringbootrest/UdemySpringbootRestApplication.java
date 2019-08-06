package com.deshmukhamit.udemyspringbootrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = {
		"application.properties",
		"mysql.application.properties",
		"jwt.application.properties",
		"cors.application.properties"
})
public class UdemySpringbootRestApplication {

	public static void main(String[] args) {

		SpringApplication.run(UdemySpringbootRestApplication.class, args);
	}

}
