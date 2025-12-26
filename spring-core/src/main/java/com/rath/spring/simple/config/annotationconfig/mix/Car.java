package com.rath.spring.simple.config.annotationconfig.mix;

//@Component
//@Primary
public class Car implements Vehicle {

	@Override
	public void move() {
		System.out.println("Car moved");
	}

}
