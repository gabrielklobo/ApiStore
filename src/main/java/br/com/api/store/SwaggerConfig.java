package br.com.api.store;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI apiInfo() {
		return new OpenAPI()
				.info(new Info()
				.title("API Store")
				.description("Documentation API Store")
				.version("v1.0"));
	}
}
