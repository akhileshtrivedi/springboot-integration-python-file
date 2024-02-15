package com.springboot.at;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "MDTI API", version = "1.0"))
public class SpringbootIntegrationPythonFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootIntegrationPythonFileApplication.class, args);
	}

}
