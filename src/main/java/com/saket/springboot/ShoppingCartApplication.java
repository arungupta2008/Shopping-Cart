package com.saket.springboot;

import com.saket.springboot.service.IProductService;
import com.saket.springboot.service.IUserService;
import com.saket.springboot.service.impl.ProductService;
import com.saket.springboot.service.impl.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ShoppingCartApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(ShoppingCartApplication.class, args);

		IProductService productService = applicationContext.getBean(IProductService.class);
		productService.saveInitialBatch();

		IUserService userService = applicationContext.getBean(IUserService.class);
		userService.saveInitialBatch();
	}
}
