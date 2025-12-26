package com.rath.spring.dynamicbinding;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;



public class Client {

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Client.class);
//		Travel t = annotationConfigApplicationContext.getBean(Travel.class);
//		t.vehicleType();
		
		Travel t = new Travel(new Bike());
		t.vehicleType();
		
	}
}
