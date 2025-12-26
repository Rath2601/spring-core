package com.rath.spring.simple.config.dynamicbinding;


public class Client {

	public static void main(String[] args) {
//		AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Client.class);
//		Travel t = annotationConfigApplicationContext.getBean(Travel.class);
//		t.vehicleType();
		
		Travel t = new Travel(new Bike());
		t.vehicleType();
		
	}
}
