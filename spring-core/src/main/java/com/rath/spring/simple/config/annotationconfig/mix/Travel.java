package com.rath.spring.annotationconfig.mix;


public class Travel {
     private Vehicle vehicle;
     
     public Travel(Vehicle vehicle) {
    	 this.vehicle = vehicle;
     }
     
     public void vehicleType() {
    	 vehicle.move();
     }
}
