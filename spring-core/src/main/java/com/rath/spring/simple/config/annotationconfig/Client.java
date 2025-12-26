package com.rath.spring.annotationconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@ComponentScan
public class Client {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Client.class);
		Travel t = annotationConfigApplicationContext.getBean(Travel.class);
		t.vehicleType();
		
	}
}
