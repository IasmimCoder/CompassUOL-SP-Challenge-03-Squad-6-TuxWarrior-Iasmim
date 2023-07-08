package br.com.compassuol.pb.challenge.products;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
// @OpenAPIDefinition(info = @Info(title = "CompassUOL-SP-Challenge-02-Squad-6-Tux-Warrior",
// description = "The application aims to create an e-commerce API establishing a relationship between customers, products and payment"))
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

}
