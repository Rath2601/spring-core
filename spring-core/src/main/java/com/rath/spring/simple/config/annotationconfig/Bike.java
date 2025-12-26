package com.rath.spring.simple.config.annotationconfig;

import org.springframework.stereotype.Component;

@Component
public class Bike implements Vehicle {

	@Override
	public void move() {
		System.out.println("Bike moved");
	}

}
