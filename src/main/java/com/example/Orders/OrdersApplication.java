package com.example.Orders;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrdersApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(OrdersApplication.class, args);
	}

}
