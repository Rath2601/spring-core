package com.rath.spring.javaconfig;

public class Car implements Vehicle {

	@Override
	public void move() {
		System.out.println("Car moved");
	}

}
