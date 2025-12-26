package com.rath.spring.javaconfig;

public class Bike implements Vehicle {

	@Override
	public void move() {
		System.out.println("Bike moved");
	}

}
