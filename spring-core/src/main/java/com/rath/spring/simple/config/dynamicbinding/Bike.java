package com.rath.spring.dynamicbinding;



public class Bike implements Vehicle {

	@Override
	public void move() {
		System.out.println("Bike moved");
	}

}
