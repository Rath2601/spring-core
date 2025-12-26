package com.rath.spring.simple.config.javaconfig;

public class Bike implements Vehicle {

	@Override
	public void move() {
		System.out.println("Bike moved");
	}

}
