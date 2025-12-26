package com.rath.spring.javaconfig;

public class Travel {
     private Vehicle vehicle;
     
     public Travel(Vehicle vehicle) {
    	 this.vehicle = vehicle;
     }
     
     public void vehicleType() {
    	 vehicle.move();
     }
}
