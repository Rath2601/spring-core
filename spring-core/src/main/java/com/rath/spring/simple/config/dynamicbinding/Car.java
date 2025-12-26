package com.rath.spring.simple.config.dynamicbinding;



public class Car implements Vehicle {

	@Override
	public void move() {
		System.out.println("Car moved");
	}

}
