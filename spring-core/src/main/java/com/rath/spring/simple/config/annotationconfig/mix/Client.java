package com.rath.spring.simple.config.annotationconfig.mix;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;



@Component
@ComponentScan
public class Client {
	
	@Bean
	public Travel travel() {
		return new Travel(bike());
	}
	
	@Bean
	public Car car() {
		return new Car();
	}
	
	@Bean
	public Bike bike() {
		return new Bike();
	}

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Client.class);
		Travel t = annotationConfigApplicationContext.getBean(Travel.class);
		t.vehicleType();
		
	}
}
