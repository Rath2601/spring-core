package com.rath.spring.simple.config.javaconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Client {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		Travel t = annotationConfigApplicationContext.getBean(Travel.class);
		t.vehicleType();
		
	}
}
