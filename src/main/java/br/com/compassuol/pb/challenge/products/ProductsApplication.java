package br.com.compassuol.pb.challenge.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;


@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Bean
	public OpenAPI customOpenApi(@Value("The application aims to create an API") String description,
								 @Value("1.0.1") String version){
		return new OpenAPI().info(new Info()
				.title("CompassUOL-SP-Challenge-02-Squad-6-Tux-Warrior_Iasmim-Product")
				.version("1.0.1")
				.termsOfService("http://swagger.io/terms")
				.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}

}
