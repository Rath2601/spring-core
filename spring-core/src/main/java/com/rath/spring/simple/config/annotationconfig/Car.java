package com.rath.spring.simple.config.annotationconfig;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Car implements Vehicle {

	@Override
	public void move() {
		System.out.println("Car moved");
	}

}
